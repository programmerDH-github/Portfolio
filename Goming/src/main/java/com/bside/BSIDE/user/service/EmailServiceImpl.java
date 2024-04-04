package com.bside.BSIDE.user.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.bside.BSIDE.contents.domain.QuestionAndAnswerDto;
import com.bside.BSIDE.service.QuestionService;
import com.bside.BSIDE.user.domain.UserDto;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender emailSender;
	private final UserService userService;
	private final QuestionService questionService;

	public EmailServiceImpl(UserService userService, QuestionService questionService) {
		this.userService = userService;
		this.questionService = questionService;
	}

	private final String senderEmail = "goming.team@gmail.com"; // 발신자 이메일 주소
	private final String senderName = "gomingSupport"; // 발신자 이름

	/* 인증코드 생성 */
	private String generateVerificationCode() {
		StringBuilder code = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < 8; i++) { // 인증코드 8자리
			int index = random.nextInt(3); // 0~2 까지 랜덤

			switch (index) {
			case 0:
				code.append((char) (random.nextInt(26) + 97)); // a~z
				break;
			case 1:
				code.append((char) (random.nextInt(26) + 65)); // A~Z
				break;
			case 2:
				code.append(random.nextInt(10)); // 0~9
				break;
			}
		}
		return code.toString();
	}

	/* 회원가입 인증코드 발송 */
	@Override
	public String sendCodeMessage(String to) throws Exception {
		String verificationCode = generateVerificationCode();

		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

		helper.setTo(to); // 수신자 이메일 주소
		helper.setSubject("&#91;Goming&#93; 인증코드를 안내해 드립니다."); // 제목

		String emailContent = "<p>Goming 회원가입을 위한 이메일 인증코드입니다.</p>" + "<br><br>"
				+ "<p>&#8226;&nbsp; 이메일 인증코드&nbsp;&nbsp;&nbsp;&nbsp;<strong>"+ verificationCode +"</strong></p>" + "<br><br>"
				+ "<p>해당 인증코드를 입력하여 이메일 주소 인증을 완료해 주세요.</p>";

		helper.setText(emailContent, true); // 내용
		helper.setFrom(new InternetAddress(senderEmail, senderName)); // 발신자 정보

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("verificationCode", verificationCode);

		try {
			emailSender.send(message);
		} catch (MailException e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return verificationCode;
	}

	/* 임시 비밀번호 발송 */
	@Override
	public String sendTemporaryPassword(String to, String temporaryPassword) throws Exception {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
	
		helper.setTo(to); // 수신자 이메일 주소
		helper.setSubject("Goming 비밀번호 찾기"); // 제목

		String emailContent = "<div style='margin:20px;'>" + "<h1>안녕하세요 이동훈입니다.</h1>" + "<br>"
				+ "<p>아래 코드를 복사해 입력해주세요.</p>" + "<br>" + "<p>감사합니다.</p>" + "<br>"
				+ "<div align='center' style='border:1px solid black; font-family:verdana;'>"
				+ "<h3 style='color:blue;'>임시 비밀번호 입니다.</h3>" + "<div style='font-size:130%'>" + "임시 비밀번호 : <strong>"
				+ temporaryPassword + "</strong><div><br/> " + "</div>";

		// 이미지 파일 경로
		String imagePath = "/Goming.JPG";

		// 이미지 파일을 첨부
		ClassPathResource resource = new ClassPathResource(imagePath);
		helper.addAttachment("Goming.JPG", resource);

		helper.setText(emailContent, true); // 내용
		helper.setFrom(new InternetAddress(senderEmail, senderName)); // 발신자 정보

		try {
			emailSender.send(message);
		} catch (MailException e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return temporaryPassword;
	}

	/* 월간고밍 페이지에서 ‘이메일로 보내기’ 버튼을 눌렀을 때 */
	@Override
	public void sendByMonth(String email, String sendEmail, String date) throws Exception {

		/* MimeMessage 생성 및 설정 */
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

		UserDto userdto = userService.getUserByEmail(email);
		System.out.println(date + "@@@데이터");
		String[] dateArr = date.split("-");

		helper.setTo(sendEmail); // 수신자 이메일 주소
		helper.setSubject("[Goming] " + userdto.getUsrNm() + "님의 월간고밍이 도착했어요!"); // 제목

		List<QuestionAndAnswerDto> questionsAndAnswers = questionService.getQuestionsAndAnswersByMonthAndEmail(email,
				date + "-01");

		/* 폰트 설정 */
		BaseFont baseFont = BaseFont.createFont("fonts/NanumGothic.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font koreanFont = new Font(baseFont, 12);

		/* PDF 객체 생성 */
		Document document = new Document();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		/* PDF 생성 */
		PdfWriter.getInstance(document, outputStream);
		document.open();
		System.out.println(questionsAndAnswers);
		if (questionsAndAnswers.isEmpty()) {
			System.out.println("if");
			document.add(new Paragraph("답변한 내용이 없습니다."));

			/* 메일 본문 작성 */
			String emailContent = "<div align='center' style='border:1px solid black; font-family:verdana;'>" + "<h3>"
					+ userdto.getUsrNm() + "님, 요청하신" + Integer.parseInt(dateArr[1]) + "월의 월간고밍은 입력하신 내용이 없습니다. :("
					+ "</h3></div>";

			/* 전송 설정 */
			helper.setText(emailContent, true); // 내용
			helper.setFrom(new InternetAddress(senderEmail, senderName)); // 발신자 정보

			/* 첨부 파일 추가 */
			byte[] pdfData = outputStream.toByteArray();
			String attachmentFilename = "NoAnswer.pdf";
			helper.addAttachment(attachmentFilename, new ByteArrayResource(pdfData));

			return;
		}

		int count = 0;

		/* PDF에 저장할 문서 작성 */
		for (QuestionAndAnswerDto dto : questionsAndAnswers) {
			if (count == 5) {
				document.newPage();
				count = 0;
				continue;
			}

			document.add(new Paragraph("Q : " + dto.getQuestion(), koreanFont));
			document.add(new Paragraph("A : " + dto.getAnswer(), koreanFont));
			document.add(new Paragraph("\n"));
			count++;

			System.out.println("Q : " + dto.getQuestion());
			System.out.println("A : " + dto.getAnswer());
			System.out.println();
		}

		document.close();

		/* 메일 본문 작성 */
		String emailContent = "<div align='center' style='border:1px solid black; font-family:verdana;'>" + "<h3>"
				+ userdto.getUsrNm() + "님, 요청하신 월간고밍이 도착했어요!<br>" + Integer.parseInt(dateArr[1])
				+ "월의 월간고밍을 확인해보세요.<br>" + "</h3></div>";

		/* 전송 설정 */
		helper.setText(emailContent, true); // 내용
		helper.setFrom(new InternetAddress(senderEmail, senderName)); // 발신자 정보

		/* 첨부 파일 추가 */
		byte[] pdfData = outputStream.toByteArray();
		String attachmentFilename = dateArr[0] + "년 " + Integer.parseInt(dateArr[1]) + "월 " + "월간고밍_"
				+ userdto.getUsrNm() + ".pdf";
		helper.addAttachment(attachmentFilename, new ByteArrayResource(pdfData));

		try {
			emailSender.send(message);
		} catch (MailException e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void sendByMonthBlob(String email, String sendEmail, String date, MultipartFile imageData0,MultipartFile imageData1,MultipartFile imageData2) throws Exception {

		/* MimeMessage 생성 및 설정 */
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

		UserDto userdto = userService.getUserByEmail(email);

		String[] dateArr = date.split("-");

		helper.setTo(sendEmail); // 수신자 이메일 주소
		helper.setSubject("[Goming] " + userdto.getUsrNm() + "님의 월간고밍이 도착했어요!"); // 제목

		/* 메일 본문 작성 */
		String emailContent = "<div align='center' style='border:1px solid black; font-family:verdana;'>" + "<h3>"
				+ userdto.getUsrNm() + "님, 요청하신 월간고밍이 도착했어요!<br>" + Integer.parseInt(dateArr[1])
				+ "월의 월간고밍을 확인해보세요.<br>" + "</h3></div>";
		/* 전송 설정 */
		helper.setText(emailContent, true); // 내용
		helper.setFrom(new InternetAddress(senderEmail, senderName)); // 발신자 정보

		/* 첨부 파일 추가 */
		String fileName = dateArr[0] + "년 " + Integer.parseInt(dateArr[1]) + "월 " + "월간고밍_" + userdto.getUsrNm()
				+ ".png";
		helper.addAttachment(fileName, imageData0, "image/png");
		if(imageData1 != null) {
			System.out.println("imageData1 있음");
			String fileName1 = dateArr[0] + "년 " + Integer.parseInt(dateArr[1]) + "월 " + "월간고밍_" + userdto.getUsrNm()
					+ "[1].png";
			helper.addAttachment(fileName1, imageData1, "image/png");

		}
		if(imageData2 != null) {

			System.out.println("imageData2 있음");
			String fileName2 = dateArr[0] + "년 " + Integer.parseInt(dateArr[1]) + "월 " + "월간고밍_" + userdto.getUsrNm()
					+ "[2].png";
			helper.addAttachment(fileName2, imageData2, "image/png");

		}

		try {
			emailSender.send(message);
		} catch (MailException e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
	}

	/* 월간 고밍 & 리마인드 메일 */
	@Override
	public void scheduleMonthlyEmail() throws Exception {
		List<UserDto> userList = userService.getUser();

		for (int i = 0; i < userList.size(); i++) {
			/* MimeMessage 생성 및 설정 */
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

			String email = userList.get(i).getEml();
			LocalDate currentDate = LocalDate.now().minusMonths(1);
			String date = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM"));

			System.out.println("email : " + email);
			System.out.println("date : " + date);

			UserDto userdto = userService.getUserByEmail(email);
			String[] dateArr = date.split("-");

			helper.setTo(email); // 수신자 이메일 주소
			helper.setSubject("[Goming] 넠넠! " + Integer.parseInt(dateArr[1]) + "월의 월간고밍이 도착했어요:)"); // 제목

			List<QuestionAndAnswerDto> questionsAndAnswers = questionService
					.getQuestionsAndAnswersByMonthAndEmail(email, date + "-01");

			/* 폰트 설정 */
			BaseFont baseFont = BaseFont.createFont("fonts/NanumGothic.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font koreanFont = new Font(baseFont, 12);

			/* PDF 객체 생성 */
			Document document = new Document();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

			/* PDF 생성 */
			PdfWriter.getInstance(document, outputStream);
			document.open();

			if (questionsAndAnswers.isEmpty()) {
				System.out.println("questionsAndAnswers.isEmpty");
				// 첨부할 내용이 없는 경우, "답변한 내용이 없습니다." 내용을 포함한 첨부파일 생성
				document.add(new Paragraph(" ", koreanFont));

				document.close();

				/* 메일 본문 작성 */
				String emailContent = "<div align='center' style='border:1px solid black; font-family:verdana;'>"
						+ "<h3>" + userdto.getUsrNm() + "님, 오늘은 어떤 하루를 보내셨나요?<br>"
						+ "지난 한달 간 기록했던 나의 하루들을 돌아볼 수 있는 월간고밍이 도착했어요!<br>" + "소중한 나의 기록들을 돌아보면서 새로운 마음으로 "
						+ (Integer.parseInt(dateArr[1]) + 1) + "월을 시작해보세요.<br><br>" + "이번 달도 " + userdto.getUsrNm()
						+ "님에게 행복한 기억으로 남는 " + (Integer.parseInt(dateArr[1]) + 1) + "월이 되기를 고밍이 응원할게요!<br>"
						+ "지금 바로 오늘의 회고 하러 가기: URL" + "</h3></div>";

				/* 전송 설정 */
				helper.setText(emailContent, true); // 내용
				helper.setFrom(new InternetAddress(senderEmail, senderName)); // 발신자 정보

				byte[] pdfData = outputStream.toByteArray();
				String attachmentFilename = dateArr[0] + "년 " + Integer.parseInt(dateArr[1]) + "월 " + "월간고밍_"
						+ userdto.getUsrNm() + ".pdf";
				helper.addAttachment(attachmentFilename, new ByteArrayResource(pdfData));

				try {
					emailSender.send(message);
				} catch (MailException e) {
					e.printStackTrace();
					throw new IllegalArgumentException();
				}

				// 빈 문서를 생성하여 첨부파일로 추가한 후에는 continue 문을 사용하여 다음 이메일로 넘어갑니다.
				continue;
			}

			/* PDF에 저장할 문서 작성 */
			for (QuestionAndAnswerDto dto : questionsAndAnswers) {
				document.add(new Paragraph("Q : " + dto.getQuestion(), koreanFont));
				document.add(new Paragraph("A : " + dto.getAnswer(), koreanFont));
				document.add(new Paragraph("\n"));
			}

			document.close();

			/* 메일 본문 작성 */
			String emailContent = "<div align='center' style='border:1px solid black; font-family:verdana;'>" + "<h3>"
					+ userdto.getUsrNm() + "님, 오늘은 어떤 하루를 보내셨나요?<br>" + "지난 한달 간 기록했던 나의 하루들을 돌아볼 수 있는 월간고밍이 도착했어요!<br>"
					+ "소중한 나의 기록들을 돌아보면서 새로운 마음으로 " + (Integer.parseInt(dateArr[1]) + 1) + "월을 시작해보세요.<br><br>"
					+ "이번 달도 " + userdto.getUsrNm() + "님에게 행복한 기억으로 남는 " + (Integer.parseInt(dateArr[1]) + 1)
					+ "월이 되기를 고밍이 응원할게요!<br>" + "지금 바로 오늘의 회고 하러 가기: URL" + "</h3></div>";

			/* 전송 설정 */
			helper.setText(emailContent, true); // 내용
			helper.setFrom(new InternetAddress(senderEmail, senderName)); // 발신자 정보

			/* 첨부 파일 추가 */
			byte[] pdfData = outputStream.toByteArray();
			String attachmentFilename = dateArr[0] + "년 " + Integer.parseInt(dateArr[1]) + "월 " + "월간고밍_"
					+ userdto.getUsrNm() + ".pdf";
			helper.addAttachment(attachmentFilename, new ByteArrayResource(pdfData));

			try {
				emailSender.send(message);
			} catch (MailException e) {
				e.printStackTrace();
				throw new IllegalArgumentException();
			}
		}
	}

}