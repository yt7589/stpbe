package com.zhuanjingkj.zjcbe.utility.mail;

import com.zhuanjingkj.zjcbe.utility.web.IpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailUtils {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JavaMailSender mailSender;//spring 提供的邮件发送类

	@Autowired
	private IpUtils ipUtils;

	@Value("${spring.mail.username:liuxiaogang}")
	private String from;

	/**
	 * 发件人
	 */
	@Value("${mail.toMail.addrs:liuxiaogang.bj@fang.com}")
	private String to;

	/**
	 * 发送文本邮件
	 *
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 */
	public void sendSimpleEmail(String subject, String content) {

		if (StringUtils.isEmpty(to)) {
			logger.error("发送邮件异常,收件人为空！");
			return;
		}

		sendSimpleEmail(to, subject, content);
	}

	/**
	 * 发送文本邮件
	 *
	 * @param to      发送人，如发送多人请用‘,’隔开
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 */
	public void sendSimpleEmail(String to, String subject, String content) {
		SimpleMailMessage message = new SimpleMailMessage();//创建简单邮件消息
		message.setFrom(from);//设置发送人
		//如果同时发送给多人
		if (to.indexOf(",") > -1) {
			String[] adds = to.split(",");
			message.setTo(adds);
		} else {
			message.setTo(to);
		}

		message.setSubject(subject);//设置主题
		message.setText(content);//设置内容
		try {
			mailSender.send(message);//执行发送邮件
			logger.info("简单邮件已经发送。");
		} catch (Exception e) {
			logger.error("发送简单邮件时发生异常！", e);
		}
	}

	/**
	 * 发送富文本邮件（带html）
	 *
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 */
	public void sendHtmlEmail(String subject, String content) {
		if (StringUtils.isEmpty(to)) {
			logger.error("发送邮件异常,收件人为空！");
			return;
		}
		sendHtmlEmail(to, subject, content);
	}

	/**
	 * 发送富文本邮件（带html）
	 *
	 * @param to      发送人，如发送多人请用‘,’隔开
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 */
	public void sendHtmlEmail(String to, String subject, String content) {
		MimeMessage message = mailSender.createMimeMessage();//创建一个MINE消息
		content = contentAdd(content);
		try {
			//true表示需要创建一个multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			//如果同时发送给多人
			if (to.indexOf(",") > -1) {
				String[] adds = to.split(",");
				helper.setTo(adds);
			} else {
				helper.setTo(to);
			}
			helper.setSubject(subject);
			helper.setText(content, true);

			mailSender.send(message);
			logger.info("html邮件发送成功");
		} catch (MessagingException e) {
			logger.error("发送html邮件时发生异常！", e);
		}

	}

	/**
	 * 发送带附件的邮件
	 *
	 * @param subject  邮件主题
	 * @param content  邮件内容
	 * @param filePath 附件路径
	 */
	public void sendAttachmentsEmail(String subject, String content, String filePath) {
		if (StringUtils.isEmpty(to)) {
			logger.error("发送邮件异常,收件人为空！");
			return;
		}
		sendAttachmentsEmail(to, subject, content, filePath);
	}

	/**
	 * 发送带附件的邮件
	 *
	 * @param to       发送人，如发送多人请用‘,’隔开
	 * @param subject  邮件主题
	 * @param content  邮件内容
	 * @param filePath 附件路径
	 */
	public void sendAttachmentsEmail(String to, String subject, String content, String filePath) {
		MimeMessage message = mailSender.createMimeMessage();//创建一个MINE消息

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			//如果同时发送给多人
			if (to.indexOf(",") > -1) {
				String[] adds = to.split(",");
				helper.setTo(adds);
			} else {
				helper.setTo(to);
			}
			helper.setSubject(subject);
			helper.setText(content, true);// true表示这个邮件是有附件的

			FileSystemResource file = new FileSystemResource(new File(filePath));//创建文件系统资源
			String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
			helper.addAttachment(fileName, file);//添加附件

			mailSender.send(message);
			logger.info("带附件的邮件已经发送。");
		} catch (MessagingException e) {
			logger.error("发送带附件的邮件时发生异常！", e);
		}

	}


	private String contentAdd(String content) {

		String exetMessage = "<p style='color:gray;top: 20px;'>";
		exetMessage += "java-magent报警中心<br/>";
		exetMessage += String.format("服务器IP：%s <br/>", ipUtils.getServerIp());
		exetMessage += "</p>";

		return content + exetMessage;
	}

}
