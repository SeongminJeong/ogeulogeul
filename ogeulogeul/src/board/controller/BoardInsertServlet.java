package board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class BoardInsertServlet
 */
@WebServlet("/BoardInsertServlet")
public class BoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8");
		//response.getWriter().append("업로드가 성공적으로 완료되었습니다.");
		
		// enctype="multipart/form-data" 로 전송되었는지 확인
		if(ServletFileUpload.isMultipartContent(request)) {
			// 전송 파일 용량 제한 : 10Mbyte 제한한 경우
			int maxSize = 1024 * 1024 * 10;

			// 웹서버 컨테이너 경로 추출함
			String root = request.getSession()
					.getServletContext().getRealPath("/");
			String root_ogeul =  "E:/Dev/java/1_JAVA/1_JAVA/ee_workspace/ogeulProject/web/";

			// 파일 저장 경로(ex : web/board_uploadFiles/) 정함
			//String savePath = root + "board_uploadFiles/";
			String savePath = root + "board_uploadFiles/";
			System.out.println(savePath);

			try {
				MultipartRequest multiRequest = 
							new MultipartRequest(request, 
								savePath, maxSize, "UTF-8",
								new DefaultFileRenamePolicy());
				
				// 업로드한 파일명 추출
				String originalFileName = 
						multiRequest.getFilesystemName("stillcut");
				String renameFileName = "";
	
				//전송된 파일이 있는 경우, 파일명 바꾸어 폴더에 기록하기
				if(originalFileName != null){
					
					String[] tailArr = originalFileName.split("\\.");
					String tail = tailArr[tailArr.length-1];
					
					if (!(tail.equals("jpg") || tail.equals("jpeg")
							|| tail.equals("gif") || tail.equals("png")
							|| tail.equals("bmp"))) {
						if (new File(savePath + "/" + originalFileName).delete() == true)
							System.out.println("정상 삭제 완료 ! ");
						response.getWriter().append(
								"<script>alert('이미지 파일만 업로드 가능합니다 ');</script>");
						return;
					}
					
					//현재 서비스가 구동된 시간정보로 파일명 바꾸기
					//예 : 20170322121532.zip
					long current = System.currentTimeMillis();
					SimpleDateFormat sdf = 
							new SimpleDateFormat("yyyyMMddHHmmss");
					//변경할 파일명 만들기
					renameFileName = sdf.format(new Date(current))
							+ "." + originalFileName.substring(
							originalFileName.lastIndexOf(".") + 1); // 확장자 
	
					File originalFile = new File(savePath + originalFileName);
					File renameFile = new File(savePath + renameFileName);
	
					//원본 파일 객체 이름 바꾸기 
					//만약 이름이 바뀌지 않으면 직접 바꿈				
					if(!originalFile.renameTo(renameFile)) {
					//rename 이 되지 않았을 경우, 강제로 파일을 복사하고
					//원본 파일은 삭제함
	
						//클라이언트로 부터 업로드될 원본 파일을 읽어서, 
						//바꿀 파일명으로 고쳐서 지정 폴더에 기록 저장하기
						FileInputStream originalRead = 
								new FileInputStream(originalFile);
						FileOutputStream renameCopy = 
								new FileOutputStream(renameFile);
	
						byte[] readText = new byte[1024];
						int readResult = 0; // the total number of bytes read into the buffer
						while ((readResult = 
								originalRead.read(readText, 0, readText.length))
								!= -1) {
							renameCopy.write(readText, 0, readResult);
							renameCopy.flush();
						}
						
						originalRead.close();
						renameCopy.close();
						originalFile.delete();
					}
				} 

			// 다른 전송값들 추출하기
			String memberId = multiRequest.getParameter("memberId");
			System.out.println("memberId : " + memberId);
			int category = Integer.parseInt(multiRequest.getParameter("category"));
			String title = multiRequest.getParameter("title");
			String content = multiRequest.getParameter("content");
			content = content.equals("")?" ":content;
			content = content.replaceAll("\r\n", "<br/>");
			String stillcut = renameFileName;
			String producer = multiRequest.getParameter("producer");

			BoardService bs = new BoardService();

			if (bs.insertBoard(new Board(bs.assignBoardNum(), 
					memberId, category, title, content, stillcut, producer)) > 0) {
				response.getWriter().append(
						"<script>alert('업로드 성공! ');"
						+ "window.parent.location.href = 'index.jsp'; "
						+ "popupClose();</script>");
			}
			else
				response.getWriter().append("<script>alert('업로드 실패! ');</script>");

			/*
			String answer = "홍길동 ";
			int category = Integer.parseInt(request.getParameter("category"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			content = content.equals("")?" ":content;
			String stillcut = request.getParameter("stillcut");
			String producer = request.getParameter("producer");
			String memberId = request.getParameter("memberId");
			*/
			} catch (IOException e) {
				System.out.println("파일 용량 초과 ");
				response.getWriter().append("<script>alert('파일 용량이 너무 큽니다 ! ');</script>");
				response.sendRedirect("popup.jsp");
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
