package com.ai.rti.ic.grp.ci.webservice;

import javax.xml.ws.WebServiceClient;

@WebServiceClient
public interface IWsSendNoticeToVgopClient {
	void sendNotice(String paramString1, String paramString2, String paramString3, String paramString4,
			String paramString5, String paramString6);

	void sendPushNotice(String paramString1, String paramString2, String paramString3, String paramString4,
			String paramString5, String paramString6, String paramString7);
}
