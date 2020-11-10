package kr.or.wic.service;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.wic.action.Action;
import kr.or.wic.action.ActionForward;
import kr.or.wic.dto.FilesDTO;
import kr.or.wic.dto.ProductDTO;

public class ProductUploadAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String uploadpath = request.getSession().getServletContext().getRealPath("upload");
		System.out.println(uploadpath);
		int size = 1024*1024*10;
		MultipartRequest multi=null;
		
		try {
			 multi = new MultipartRequest(
					request, //기존에 있는  request 객체의 주소값 
					uploadpath, //실 저장 경로 (배포경로)
					size, //10M
					"UTF-8",
					new DefaultFileRenamePolicy() //파일 중복(upload 폴더 안에:a.jpg -> a_1.jpg(업로드 파일 변경) )
					);
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("여긴가?");
		} //파일 업로드 완료
			
			Enumeration filenames = multi.getFileNames();
			String productName = multi.getParameter("productName");
			String location = multi.getParameter("location");
			int productPrice = Integer.parseInt(multi.getParameter("productPrice"));
			String context = multi.getParameter("context");
			String filename = multi.getFilesystemName((String)filenames.nextElement());
			System.out.println(filename);
			
			FilesDTO fdto = new FilesDTO();
			ProductDTO pdto = new ProductDTO();
			pdto.setPrd_title(productName);
			pdto.setPrd_price(productPrice);
			pdto.setPrd_content(context);
			
			ActionForward forward = new ActionForward();
			forward.setPath("mypage.html");
			
			
		
		
		
		
		return forward;
	}

}