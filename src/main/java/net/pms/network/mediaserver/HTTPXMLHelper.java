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
package net.pms.network.mediaserver;

public class HTTPXMLHelper {
	private static final String CRLF = "\r\n";
	public static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
	public static final String SOAP_ENCODING_HEADER = "<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\" s:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">" + CRLF + "<s:Body>";
	public static final String SOAP_ENCODING_FOOTER = "</s:Body>" + CRLF + "</s:Envelope>";
	public static final String GETSYSTEMUPDATEID_HEADER = "<u:GetSystemUpdateIDResponse xmlns:u=\"urn:schemas-upnp-org:service:ContentDirectory:1\">";
	public static final String GETSYSTEMUPDATEID_FOOTER = "</u:GetSystemUpdateIDResponse>";
	public static final String BROWSERESPONSE_HEADER = "<u:BrowseResponse xmlns:u=\"urn:schemas-upnp-org:service:ContentDirectory:1\">";
	public static final String BROWSERESPONSE_FOOTER = "</u:BrowseResponse>";
	public static final String SEARCHRESPONSE_HEADER = "<u:SearchResponse xmlns:u=\"urn:schemas-upnp-org:service:ContentDirectory:1\">";
	public static final String SEARCHRESPONSE_FOOTER = "</u:SearchResponse>";
	public static final String SETBOOKMARK_RESPONSE = "<u:X_SetBookmarkResponse xmlns:u=\"urn:schemas-upnp-org:service:ContentDirectory:1\"></u:X_SetBookmarkResponse>";
	public static final String SORTCAPS_RESPONSE = "<u:GetSortCapabilitiesResponse xmlns:u=\"urn:schemas-upnp-org:service:ContentDirectory:1\"><SortCaps></SortCaps></u:GetSortCapabilitiesResponse>";
	public static final String SEARCHCAPS_RESPONSE = "<u:GetSearchCapabilitiesResponse xmlns:u=\"urn:schemas-upnp-org:service:ContentDirectory:1\"><SearchCaps>upnp:class,dc:title,dc:creator,upnp:artist,upnp:album,upnp:genre</SearchCaps></u:GetSearchCapabilitiesResponse>";
	public static final String SEARCHCAPS_RESPONSE_SEARCH_DEACTIVATED = "<u:GetSearchCapabilitiesResponse xmlns:u=\"urn:schemas-upnp-org:service:ContentDirectory:1\"><SearchCaps></SearchCaps></u:GetSearchCapabilitiesResponse>";
	public static final String PROTOCOLINFO_RESPONSE =
		"<u:GetProtocolInfoResponse xmlns:u=\"urn:schemas-upnp-org:service:ConnectionManager:1\"><Source>" +
		"http-get:*:image/jpeg:DLNA.ORG_PN=JPEG_TN," +
		"http-get:*:image/jpeg:DLNA.ORG_PN=JPEG_SM," +
		"http-get:*:image/jpeg:DLNA.ORG_PN=JPEG_MED," +
		"http-get:*:image/jpeg:DLNA.ORG_PN=JPEG_LRG," +
		"http-get:*:image/jpeg:DLNA.ORG_PN=JPEG_RES_H_V," +
		"http-get:*:image/png:DLNA.ORG_PN=PNG_TN," +
		"http-get:*:image/png:DLNA.ORG_PN=PNG_LRG," +
		"http-get:*:image/gif:DLNA.ORG_PN=GIF_LRG," +
		"http-get:*:audio/mpeg:DLNA.ORG_PN=MP3," +
		"http-get:*:audio/L16:DLNA.ORG_PN=LPCM," +
		"http-get:*:video/mpeg:DLNA.ORG_PN=AVC_TS_HD_24_AC3_ISO;SONY.COM_PN=AVC_TS_HD_24_AC3_ISO," +
		"http-get:*:video/vnd.dlna.mpeg-tts:DLNA.ORG_PN=AVC_TS_HD_24_AC3;SONY.COM_PN=AVC_TS_HD_24_AC3," +
		"http-get:*:video/vnd.dlna.mpeg-tts:DLNA.ORG_PN=AVC_TS_HD_24_AC3_T;SONY.COM_PN=AVC_TS_HD_24_AC3_T," +
		"http-get:*:video/vnd.dlna.mpeg-tts:DLNA.ORG_PN=MPEG_PS_PAL," +
		"http-get:*:video/vnd.dlna.mpeg-tts:DLNA.ORG_PN=MPEG_PS_NTSC," +
		"http-get:*:video/vnd.dlna.mpeg-tts:DLNA.ORG_PN=MPEG_TS_SD_50_L2_T," +
		"http-get:*:video/vnd.dlna.mpeg-tts:DLNA.ORG_PN=MPEG_TS_SD_60_L2_T," +
		"http-get:*:video/mpeg:DLNA.ORG_PN=MPEG_TS_SD_EU_ISO," +
		"http-get:*:video/vnd.dlna.mpeg-tts:DLNA.ORG_PN=MPEG_TS_SD_EU," +
		"http-get:*:video/vnd.dlna.mpeg-tts:DLNA.ORG_PN=MPEG_TS_SD_EU_T," +
		"http-get:*:video/vnd.dlna.mpeg-tts:DLNA.ORG_PN=MPEG_TS_SD_50_AC3_T," +
		"http-get:*:video/mpeg:DLNA.ORG_PN=MPEG_TS_HD_50_L2_ISO;SONY.COM_PN=HD2_50_ISO," +
		"http-get:*:video/vnd.dlna.mpeg-tts:DLNA.ORG_PN=MPEG_TS_SD_60_AC3_T," +
		"http-get:*:video/mpeg:DLNA.ORG_PN=MPEG_TS_HD_60_L2_ISO;SONY.COM_PN=HD2_60_ISO," +
		"http-get:*:video/vnd.dlna.mpeg-tts:DLNA.ORG_PN=MPEG_TS_HD_50_L2_T;SONY.COM_PN=HD2_50_T," +
		"http-get:*:video/vnd.dlna.mpeg-tts:DLNA.ORG_PN=MPEG_TS_HD_60_L2_T;SONY.COM_PN=HD2_60_T," +
		"http-get:*:video/mpeg:DLNA.ORG_PN=AVC_TS_HD_50_AC3_ISO;SONY.COM_PN=AVC_TS_HD_50_AC3_ISO," +
		"http-get:*:video/vnd.dlna.mpeg-tts:DLNA.ORG_PN=AVC_TS_HD_50_AC3;SONY.COM_PN=AVC_TS_HD_50_AC3," +
		"http-get:*:video/mpeg:DLNA.ORG_PN=AVC_TS_HD_60_AC3_ISO;SONY.COM_PN=AVC_TS_HD_60_AC3_ISO," +
		"http-get:*:video/vnd.dlna.mpeg-tts:DLNA.ORG_PN=AVC_TS_HD_60_AC3;SONY.COM_PN=AVC_TS_HD_60_AC3," +
		"http-get:*:video/vnd.dlna.mpeg-tts:DLNA.ORG_PN=AVC_TS_HD_50_AC3_T;SONY.COM_PN=AVC_TS_HD_50_AC3_T," +
		"http-get:*:video/vnd.dlna.mpeg-tts:DLNA.ORG_PN=AVC_TS_HD_60_AC3_T;SONY.COM_PN=AVC_TS_HD_60_AC3_T," +
		"http-get:*:video/x-mp2t-mphl-188:*," +
		"http-get:*:*:*," +
		"http-get:*:video/*:*," +
		"http-get:*:audio/*:*," +
		"http-get:*:image/*:*" +
		"</Source><Sink></Sink></u:GetProtocolInfoResponse>";
	public static final String RESULT_HEADER = "<Result>";
	public static final String RESULT_FOOTER = "</Result>";
	public static final String DIDL_HEADER = "&lt;DIDL-Lite xmlns=\"urn:schemas-upnp-org:metadata-1-0/DIDL-Lite/\" xmlns:dc=\"http://purl.org/dc/elements/1.1/\" xmlns:upnp=\"urn:schemas-upnp-org:metadata-1-0/upnp/\" xmlns:sec=\"http://www.sec.co.kr/\" xmlns:pv=\"http://www.pv.com/pvns/\"&gt;";
	public static final String DIDL_FOOTER = "&lt;/DIDL-Lite&gt;";
	public static final String XBOX_360_1 = "<u:IsValidatedResponse xmlns:u=\"urn:microsoft.com:service:X_MS_MediaReceiverRegistrar:1\">" + CRLF + "<Result>1</Result>" + CRLF + "</u:IsValidatedResponse>";
	public static final String XBOX_360_2 = "<u:IsAuthorizedResponse xmlns:u=\"urn:microsoft.com:service:X_MS_MediaReceiverRegistrar:1\">" + CRLF + "<Result>1</Result>" + CRLF + "</u:IsAuthorizedResponse>";
	public static final String SAMSUNG_ERROR_RESPONSE = "<s:Fault><faultCode>s:Client</faultCode><faultString>UPnPError</faultString><detail><UPnPError xmlns=\"urn:schemas-upnp-org:control-1-0\"><errorCode>401</errorCode><errorDescription>Invalid Action</errorDescription></UPnPError></detail></s:Fault>";
	public static final String EVENT_FOOTER = "</e:propertyset>";

	/**
	 * This class is not meant to be instantiated.
	 */
	private HTTPXMLHelper() {
	}

	public static String eventProp(String prop) {
		return eventProp(prop, "");
	}

	public static String eventProp(String prop, String val) {
		return "<e:property><" + prop + ">" + val + "</" + prop + "></e:property>";
	}

	public static String eventHeader(String urn) {
		return "<e:propertyset xmlns:e=\"urn:schemas-upnp-org:event-1-0\" xmlns:s=\"" + urn + "\">";
	}
}
