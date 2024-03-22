package com.abclaboratary.abclaboratary.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

@Component
public class Common { 
	
	public boolean sendEMail(String to, String subject, String body) {
		try {

			Properties properties = System.getProperties();
			boolean debug = false;
		
			String email ="abclaboratory.lab@gmail.com";
			String Password ="ogal ljpi eywu evru";
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "587");
			properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			
			
			
			Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(email, Password);
				}
			});

			Message message = new MimeMessage(session);

			message.addFrom(new InternetAddress[] { new InternetAddress("abclaboratory.lab@gmail.com") });

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			
			message.setSubject(subject);
			message.setContent(body, "text/html");
			
			Transport.send(message);

			System.out.println("Done");

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String generateRandomPassword() {

		final Random RANDOM = new SecureRandom();
		/** Length of password. @see #generateRandomPassword() */
		final int PASSWORD_LENGTH = 8;
		// Pick from some letters that won't be easily mistaken for each
		// other. So, for example, omit o O and 0, 1 l and L.
		String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789+@";

		String randompw = "";
		for (int i = 0; i < PASSWORD_LENGTH; i++) {
			int index = (int) (RANDOM.nextDouble() * letters.length());
			randompw += letters.substring(index, index + 1);
		}
		System.out.println("Gen Ran Pass " + randompw);
		System.out.println("Gen Ran Pass and send mail here" + randompw);

		String encryptedpassword = getEncryptedPassword(randompw);
		System.out.println("encrpted " + encryptedpassword);
		return randompw;
	}
	
	@SuppressWarnings("restriction")
	public static String getEncryptedPassword(String clearTextPassword) {

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(clearTextPassword.getBytes());
			return new sun.misc.BASE64Encoder().encode(md.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			// _log.error("Failed to encrypt password.", e);
		}
		return "";
	}
	
	
	public static String emailabove = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n"
			+ "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\r\n"
			+ "<head>\r\n"
			+ "\r\n"
			+ "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n"
			+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
			+ "  <meta name=\"x-apple-disable-message-reformatting\">\r\n"
			+ " <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
			+ "  <title></title>\r\n"
			+ "  \r\n"
			+ "    <style type=\"text/css\">\r\n"
			+ "      @media only screen and (min-width: 620px) {\r\n"
			+ "  .u-row {\r\n"
			+ "    width: 600px !important;\r\n"
			+ "  }\r\n"
			+ "  .u-row .u-col {\r\n"
			+ "    vertical-align: top;\r\n"
			+ "  }\r\n"
			+ "\r\n"
			+ "  .u-row .u-col-50 {\r\n"
			+ "    width: 300px !important;\r\n"
			+ "  }\r\n"
			+ "\r\n"
			+ "  .u-row .u-col-100 {\r\n"
			+ "    width: 600px !important;\r\n"
			+ "  }\r\n"
			+ "\r\n"
			+ "}\r\n"
			+ "\r\n"
			+ "@media (max-width: 620px) {\r\n"
			+ "  .u-row-container {\r\n"
			+ "    max-width: 100% !important;\r\n"
			+ "    padding-left: 0px !important;\r\n"
			+ "    padding-right: 0px !important;\r\n"
			+ "  }\r\n"
			+ "  .u-row .u-col {\r\n"
			+ "    min-width: 320px !important;\r\n"
			+ "    max-width: 100% !important;\r\n"
			+ "    display: block !important;\r\n"
			+ "  }\r\n"
			+ "  .u-row {\r\n"
			+ "    width: calc(100% - 40px) !important;\r\n"
			+ "  }\r\n"
			+ "  .u-col {\r\n"
			+ "    width: 100% !important;\r\n"
			+ "  }\r\n"
			+ "  .u-col > div {\r\n"
			+ "    margin: 0 auto;\r\n"
			+ "  }\r\n"
			+ "}\r\n"
			+ "body {\r\n"
			+ "  margin: 0;\r\n"
			+ "  padding: 0;\r\n"
			+ "}\r\n"
			+ "\r\n"
			+ "table,\r\n"
			+ "tr,\r\n"
			+ "td {\r\n"
			+ "  vertical-align: top;\r\n"
			+ "  border-collapse: collapse;\r\n"
			+ "}\r\n"
			+ "\r\n"
			+ "p {\r\n"
			+ "  margin: 0;\r\n"
			+ "}\r\n"
			+ "\r\n"
			+ ".ie-container table,\r\n"
			+ ".mso-container table {\r\n"
			+ "  table-layout: fixed;\r\n"
			+ "}\r\n"
			+ "\r\n"
			+ "* {\r\n"
			+ "  line-height: inherit;\r\n"
			+ "}\r\n"
			+ "\r\n"
			+ "a[x-apple-data-detectors='true'] {\r\n"
			+ "  color: inherit !important;\r\n"
			+ "  text-decoration: none !important;\r\n"
			+ "}\r\n"
			+ "\r\n"
			+ "table, td { color: #000000; } </style>\r\n"
			+ "  \r\n"
			+ "  \r\n"
			+ "\r\n"
			+ "<link href=\"https://fonts.googleapis.com/css2?family=Fredoka+One&display=swap\" rel=\"stylesheet\" type=\"text/css\"><link href=\"https://fonts.googleapis.com/css?family=Lato:400,700&display=swap\" rel=\"stylesheet\" type=\"text/css\">\r\n"
			+ "\r\n"
			+ "</head>\r\n"
			+ "\r\n"
			+ "<body class=\"clean-body u_body\" style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;background-color: #f9f9f9;color: #000000\">\r\n"
			+ "\r\n"
			+ "  <table style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;min-width: 320px;Margin: 0 auto;background-color: #f9f9f9;width:100%\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
			+ "  <tbody>\r\n"
			+ "  <tr style=\"vertical-align: top\">\r\n"
			+ "    <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\r\n"
			+ "   \r\n"
			+ "    \r\n"
			+ "\r\n"
			+ "<div class=\"u-row-container\" style=\"padding: 0px;background-color: #f9f9f9\">\r\n"
			+ "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #f9f9f9;\">\r\n"
			+ "    <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\r\n"
			+ "     \r\n"
			+ "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\r\n"
			+ "  <div style=\"height: 100%;width: 100% !important;\">\r\n"
			+ " <div style=\"height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\r\n"
			+ "  \r\n"
			+ "<table style=\"font-family:'Lato',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n"
			+ "  <tbody>\r\n"
			+ "    <tr>\r\n"
			+ "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:15px;font-family:'Lato',sans-serif;\" align=\"left\">\r\n"
			+ "        \r\n"
			+ "  <table height=\"0px\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 1px solid #f9f9f9;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\r\n"
			+ "    <tbody>\r\n"
			+ "      <tr style=\"vertical-align: top\">\r\n"
			+ "        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\r\n"
			+ "          <span>&#160;</span>\r\n"
			+ "        </td>\r\n"
			+ "      </tr>\r\n"
			+ "    </tbody>\r\n"
			+ "  </table>\r\n"
			+ "\r\n"
			+ "      </td>\r\n"
			+ "    </tr>\r\n"
			+ "  </tbody>\r\n"
			+ "</table>\r\n"
			+ "\r\n"
			+ "  \r\n"
			+ "  </div>\r\n"
			+ "</div>\r\n"
			+ "\r\n"
			+ "    </div>\r\n"
			+ "  </div>\r\n"
			+ "</div>\r\n"
			+ "\r\n"
			+ "\r\n"
			+ "\r\n"
			+ "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\r\n"
			+ "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\r\n"
			+ "    <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\r\n"
			+ "      \r\n"
			+ "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\r\n"
			+ "  <div style=\"height: 100%;width: 100% !important;\">\r\n"
			+ "<div style=\"height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\r\n"
			+ "  </div>\r\n"
			+ "  </div>\r\n"
			+ "</div>\r\n"
			+ "\r\n"
			+ "    </div>\r\n"
			+ "  </div>\r\n"
			+ "</div>\r\n"
			+ "\r\n"
			+ "\r\n"
			+ "\r\n"
			+ "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\r\n"
			+ "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #0b883f;\">\r\n"
			+ "    <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\r\n"
			+ "      \r\n"
			+ "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\r\n"
			+ "  <div style=\"height: 100%;width: 100% !important;\">\r\n"
			+ "<div style=\"height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\r\n"
			+ "  \r\n"
			+ "<table style=\"font-family:'Lato',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n"
			+ "  <tbody>\r\n"
			+ "    <tr>\r\n"
			+ "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:0px 10px 30px;font-family:'Lato',sans-serif;\" align=\"left\">\r\n"
			+ "        \r\n"
			+ "  <div style=\"line-height: 140%; text-align: left; word-wrap: break-word;\">\r\n"
			+ "    <p style=\"font-size: 14px; line-height: 140%; text-align: center;\"> </p>\r\n"
			+ "<p style=\"font-size: 14px; line-height: 140%; text-align: center;\"><span style=\"font-size: 28px; line-height: 39.2px; color: #ffffff; font-family: Lato, sans-serif;\">ABC LABORATORY</span></p>\r\n"
			+ "  </div>\r\n"
			+ "\r\n"
			+ "      </td>\r\n"
			+ "    </tr>\r\n"
			+ "  </tbody>\r\n"
			+ "</table>\r\n"
			+ "\r\n"
			+ "  \r\n"
			+ "  </div>\r\n"
			+ "</div>\r\n"
			+ "\r\n"
			+ "    </div>\r\n"
			+ "  </div>\r\n"
			+ "</div>\r\n"
			+ "\r\n"
			+ "\r\n"
			+ "\r\n"
			+ "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\r\n"
			+ "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\r\n"
			+ "    <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\r\n"
			+ "      \r\n"
			+ "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\r\n"
			+ "  <div style=\"height: 100%;width: 100% !important;\">\r\n"
			+ " <div style=\"height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\r\n"
			+ "  \r\n"
			+ "<table style=\"font-family:'Lato',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n"
			+ "  <tbody>\r\n"
			+ "    <tr>\r\n"
			+ "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:40px 40px 30px;font-family:'Lato',sans-serif;\" align=\"left\">";

		
	public static String emailabelow = "</td>\r\n"
			+ "    </tr>\r\n"
			+ "  </tbody>\r\n"
			+ "</table>\r\n"
			+ "\r\n"
			+ "<table style=\"font-family:'Lato',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n"
			+ "  <tbody>\r\n"
			+ "    <tr>\r\n"
			+ "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:40px 40px 30px;font-family:'Lato',sans-serif;\" align=\"left\">\r\n"
			+ "        \r\n"
			+ "  <div style=\"line-height: 140%; text-align: left; word-wrap: break-word;\">\r\n"
			+ "    <p style=\"font-size: 14px; line-height: 140%;\"><span style=\"color: #888888; font-size: 14px; line-height: 19.6px;\"><em><span style=\"font-size: 16px; line-height: 22.4px;\">Don't reply to this email.</span></em></span><br /><span style=\"color: #888888; font-size: 14px; line-height: 19.6px;\"><em><span style=\"font-size: 16px; line-height: 22.4px;\"> </span></em></span></p>\r\n"
			+ "  </div>\r\n"
			+ "\r\n"
			+ "      </td>\r\n"
			+ "    </tr>\r\n"
			+ "  </tbody>\r\n"
			+ "</table>\r\n"
			+ "\r\n"
			+ "  \r\n"
			+ "  </div>\r\n"
			+ "</div>\r\n"
			+ "\r\n"
			+ "    </div>\r\n"
			+ "  </div>\r\n"
			+ "</div>\r\n"
			+ "\r\n"
			+ "\r\n"
			+ "\r\n"
			+ "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\r\n"
			+ "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #0b883f;\">\r\n"
			+ "    <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\r\n"
			+ "     \r\n"
			+ "<div class=\"u-col u-col-50\" style=\"max-width: 320px;min-width: 300px;display: table-cell;vertical-align: top;\">\r\n"
			+ "  <div style=\"height: 100%;width: 100% !important;\">\r\n"
			+ "  <div style=\"height: 100%; padding: 20px 20px 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\r\n"
			+ "  \r\n"
			+ "<table style=\"font-family:'Lato',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n"
			+ "  <tbody>\r\n"
			+ "    <tr>\r\n"
			+ "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:'Lato',sans-serif;\" align=\"left\">\r\n"
			+ "        \r\n"
			+ "  <div style=\"line-height: 140%; text-align: left; word-wrap: break-word;\">\r\n"
			+ "    <p style=\"font-size: 14px; line-height: 140%;\"><span style=\"font-size: 16px; line-height: 22.4px; color: #ecf0f1;\">ABC Laboratory</span></p>\r\n"
			+ "<p style=\"font-size: 14px; line-height: 140%;\"><span style=\"font-size: 14px; line-height: 19.6px; color: #ecf0f1;\">40, Nawam Mawatha, Colombo 2, Sri Lanka</span></p>\r\n"
			+ "<p style=\"font-size: 14px; line-height: 140%;\"><span style=\"font-size: 14px; line-height: 19.6px; color: #ecf0f1;\">T: +94 11 244 8448 | Ext. 31152</span></p>\r\n"
			+ "  </div>\r\n"
			+ "\r\n"
			+ "      </td>\r\n"
			+ "    </tr>\r\n"
			+ "  </tbody>\r\n"
			+ "</table>\r\n"
			+ "\r\n"
			+ " \r\n"
			+ "  </div>\r\n"
			+ "</div>\r\n"
			+ "\r\n"
			+ "<div class=\"u-col u-col-50\" style=\"max-width: 320px;min-width: 300px;display: table-cell;vertical-align: top;\">\r\n"
			+ "  <div style=\"height: 100%;width: 100% !important;\">\r\n"
			+ " <div style=\"height: 100%; padding: 0px 0px 0px 20px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\r\n"
			+ "  \r\n"
			+ "<table style=\"font-family:'Lato',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n"
			+ "  <tbody>\r\n"
			+ "    <tr>\r\n"
			+ "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:5px 10px 10px;font-family:'Lato',sans-serif;\" align=\"left\">\r\n"
			+ "        \r\n"
			+ "  <div style=\"line-height: 140%; text-align: left; word-wrap: break-word;\">\r\n"
			+ "    <p style=\"line-height: 140%; font-size: 14px;\"> </p>\r\n"
			+ "<p style=\"line-height: 140%; font-size: 14px;\"> </p>\r\n"
			+ "<p style=\"line-height: 140%; font-size: 14px;\"> </p>\r\n"
			+ "<p style=\"line-height: 140%; font-size: 14px;\"> </p>\r\n"
			+ "<p style=\"line-height: 140%; font-size: 14px;\"><span style=\"font-size: 14px; line-height: 19.6px;\"><span style=\"color: #ecf0f1; font-size: 14px; line-height: 19.6px;\"><span style=\"line-height: 19.6px; font-size: 14px;\">ABC Laboratory © All Rights Reserved</span></span></span></p>\r\n"
			+ "  </div>\r\n"
			+ "\r\n"
			+ "      </td>\r\n"
			+ "    </tr>\r\n"
			+ "  </tbody>\r\n"
			+ "</table>\r\n"
			+ "\r\n"
			+ "  \r\n"
			+ "  </div>\r\n"
			+ "</div>\r\n"
			+ "\r\n"
			+ "    </div>\r\n"
			+ "  </div>\r\n"
			+ "</div>\r\n"
			+ "\r\n"
			+ "\r\n"
			+ "\r\n"
			+ "<div class=\"u-row-container\" style=\"padding: 0px;background-color: #f9f9f9\">\r\n"
			+ "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #0b883f;\">\r\n"
			+ "    <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\r\n"
			+ "      \r\n"
			+ "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\r\n"
			+ "  <div style=\"height: 100%;width: 100% !important;\">\r\n"
			+ "<div style=\"height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\r\n"
			+ "  \r\n"
			+ "<table style=\"font-family:'Lato',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n"
			+ "  <tbody>\r\n"
			+ "    <tr>\r\n"
			+ "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:15px;font-family:'Lato',sans-serif;\" align=\"left\">\r\n"
			+ "        \r\n"
			+ "  <table height=\"0px\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 1px solid #1c103b;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\r\n"
			+ "    <tbody>\r\n"
			+ "      <tr style=\"vertical-align: top\">\r\n"
			+ "        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\r\n"
			+ "          <span>&#160;</span>\r\n"
			+ "        </td>\r\n"
			+ "      </tr>\r\n"
			+ "    </tbody>\r\n"
			+ "  </table>\r\n"
			+ "\r\n"
			+ "      </td>\r\n"
			+ "    </tr>\r\n"
			+ "  </tbody>\r\n"
			+ "</table>\r\n"
			+ "\r\n"
			+ " </div>\r\n"
			+ "  </div>\r\n"
			+ "</div>\r\n"
			+ "\r\n"
			+ "    </div>\r\n"
			+ "  </div>\r\n"
			+ "</div>\r\n"
			+ "\r\n"
			+ "\r\n"
			+ "\r\n"
			+ "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\r\n"
			+ "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #f9f9f9;\">\r\n"
			+ "    <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\r\n"
			+ "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #f9f9f9;\"><![endif]-->\r\n"
			+ "      \r\n"
			+ "<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\r\n"
			+ "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\r\n"
			+ "  <div style=\"height: 100%;width: 100% !important;\">\r\n"
			+ "  <!--[if (!mso)&(!IE)]><!--><div style=\"height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\r\n"
			+ "  \r\n"
			+ "<table style=\"font-family:'Lato',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n"
			+ "  <tbody>\r\n"
			+ "    <tr>\r\n"
			+ "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:0px 40px 30px 20px;font-family:'Lato',sans-serif;\" align=\"left\">\r\n"
			+ "        \r\n"
			+ "  <div style=\"line-height: 140%; text-align: left; word-wrap: break-word;\">\r\n"
			+ "    \r\n"
			+ "  </div>\r\n"
			+ "\r\n"
			+ "      </td>\r\n"
			+ "    </tr>\r\n"
			+ "  </tbody>\r\n"
			+ "</table>\r\n"
			+ "\r\n"
			+ "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\r\n"
			+ "  </div>\r\n"
			+ "</div>\r\n"
			+ "<!--[if (mso)|(IE)]></td><![endif]-->\r\n"
			+ "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\r\n"
			+ "    </div>\r\n"
			+ "  </div>\r\n"
			+ "</div>\r\n"
			+ "\r\n"
			+ "\r\n"
			+ "    <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\r\n"
			+ "    </td>\r\n"
			+ "  </tr>\r\n"
			+ "  </tbody>\r\n"
			+ "  </table>\r\n"
			+ "  <!--[if mso]></div><![endif]-->\r\n"
			+ "  <!--[if IE]></div><![endif]-->\r\n"
			+ "</body>\r\n"
			+ "\r\n"
			+ "</html>\r\n"
			+ "";


	public static String getEmailabove() {
		return emailabove;
	}


	public static void setEmailabove(String emailabove) {
		Common.emailabove = emailabove;
	}


	public static String getEmailabelow() {
		return emailabelow;
	}


	public static void setEmailabelow(String emailabelow) {
		Common.emailabelow = emailabelow;
	}
	
	

}
