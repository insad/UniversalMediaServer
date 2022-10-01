/*
 * This file is part of Universal Media Server, based on PS3 Media Server.
 *
 * This program is a free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; version 2
 * of the License only.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package net.pms.configuration;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import net.pms.PMS;
import net.pms.util.FilePermissions;
import net.pms.util.FileUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mfranco
 */
public class MapFileConfiguration {
	private static final Logger LOGGER = LoggerFactory.getLogger(MapFileConfiguration.class);
	private static final PmsConfiguration CONFIGURATION = PMS.getConfiguration();
	private String name;
	private List<MapFileConfiguration> children;
	private List<File> files;

	private boolean addToMediaLibrary = true;

	public String getName() {
		return name;
	}

	public List<MapFileConfiguration> getChildren() {
		return children;
	}

	public List<File> getFiles() {
		return files;
	}

	public boolean isAddToMediaLibrary() {
		return addToMediaLibrary;
	}

	public void setName(String n) {
		name = n;
	}

	public void setFiles(List<File> f) {
		files = f;
	}

	public void setAddToMediaLibrary(boolean addToMediaLibrary) {
		this.addToMediaLibrary = addToMediaLibrary;
	}

	public MapFileConfiguration() {
		children = new ArrayList<>();
		files = new ArrayList<>();
	}

	public static List<MapFileConfiguration> parseVirtualFolders() {
		String conf;

		if (isNotBlank(CONFIGURATION.getVirtualFoldersFile())) {
			// Get the virtual folder info from the user's file
			conf = CONFIGURATION.getVirtualFoldersFile().trim().replaceAll("&comma;", ",");
			File file = new File(CONFIGURATION.getProfileDirectory(), conf);
			conf = null;

			try {
				conf = FileUtils.readFileToString(file, StandardCharsets.US_ASCII);
			} catch (IOException e) {
				LOGGER.warn("Unexpected exeption while reading \"{}\": {}", file.getAbsolutePath(), e.getMessage());
				LOGGER.debug("", e);
				return null;
			}
		} else if (isNotBlank(CONFIGURATION.getVirtualFolders())) {
			// Get the virtual folder info from the config string
			conf = CONFIGURATION.getVirtualFolders().trim().replaceAll("&comma;", ",");

			// Convert our syntax into JSON syntax
			String[] arrayLevel0 = conf.split(";");
			StringBuilder jsonStringFromConf = new StringBuilder();
			jsonStringFromConf.append("[");
			boolean firstLoop = true;
			int i = 0;
			for (String folders : arrayLevel0) {
				String[] arrayLevel1 = folders.split("\\|");
				if (!firstLoop) {
					jsonStringFromConf.append(',');
				}

				for (String value : arrayLevel1) {
					if (i == 0) {
						jsonStringFromConf.append("{\"name\":\"").append(value).append("\",\"files\":[");
						i++;
					} else {
						String[] arrayLevel2 = value.split(",");
						boolean firstFile = true;
						for (String value2 : arrayLevel2) {
							if (!firstFile) {
								jsonStringFromConf.append(",");
							}

							jsonStringFromConf.append("\"").append(value2).append("\"");
							firstFile = false;
						}

						jsonStringFromConf.append("]}");
					}
				}

				firstLoop = false;
				i = 0;
			}

			jsonStringFromConf.append("]");

			conf = jsonStringFromConf.toString().replace("\\\\", "\\\\\\\\");

		} else {
			return null;
		}

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(File.class, new FileSerializer());
		Gson gson = gsonBuilder.create();
		Type listType = (new TypeToken<ArrayList<MapFileConfiguration>>() { }).getType();
		return gson.fromJson(conf, listType);
	}
}

class FileSerializer implements JsonSerializer<File>, JsonDeserializer<File> {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileSerializer.class);

	@Override
	public JsonElement serialize(File src, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(src.getAbsolutePath());
	}

	@Override
	public File deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		File file = new File(json.getAsJsonPrimitive().getAsString());

		try {
			FilePermissions permissions = FileUtil.getFilePermissions(file);
			if (permissions.isBrowsable()) {
				return file;
			}
			LOGGER.warn("Insufficient permission to read folder \"{}\": {}", file.getAbsolutePath(), permissions.getLastCause());
			return null;
		} catch (FileNotFoundException e) {
			LOGGER.warn("Folder not found: {}", e.getMessage());
			return null;
		}
	}
}
