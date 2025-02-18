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
package net.pms.network.mediaserver.handlers;

import net.pms.PMS;
import net.pms.configuration.PmsConfiguration;
import net.pms.service.LibraryScanner;
import net.pms.util.PropertiesUtil;

public class HTMLConsole {
	/**
	 * This class is not meant to be instantiated.
	 */
	private HTMLConsole() {
	}

	public static String servePage(String resource) {
		StringBuilder sb = new StringBuilder();
		sb.append("<html><head><meta charset=\"utf-8\"><title>").append(PropertiesUtil.getProjectProperties().get("project.name")).append(" HTML Console</title></head><body>");

		PmsConfiguration configuration = PMS.getConfiguration();

		if (resource.equals("scan") && configuration.getUseCache()) {
			if (!LibraryScanner.isScanLibraryRunning()) {
				LibraryScanner.scanLibrary();
			}
			if (LibraryScanner.isScanLibraryRunning()) {
				sb.append("<p style=\"margin: 0 auto; text-align: center;\"><b>Scan in progress! you can also <a href=\"stop\">stop it</a></b></p><br>");
			}
		}

		if (resource.equals("stop") && configuration.getUseCache() && LibraryScanner.isScanLibraryRunning()) {
			LibraryScanner.stopScanLibrary();
			sb.append("<p style=\"margin: 0 auto; text-align: center;\"><b>Scan stopped!</b></p><br>");
		}

		sb.append("<p style=\"margin: 0 auto; text-align: center;\"><img src='/images/logo.png'><br>").append(PropertiesUtil.getProjectProperties().get("project.name")).append(" HTML console<br><br>Menu:<br>");
		sb.append("<a href=\"home\">Home</a><br>");
		sb.append("<a href=\"scan\">Scan folders</a><br>");
		sb.append("</p></body></html>");
		return sb.toString();
	}
}
