package dal;

import java.util.ArrayList;
import java.util.List;

import dto.Pages;

public class PaginationDAO {

	
	static List<Pages> paginate(String fileContent){
		int pageSize = 100;
		int pageNumber = 1;
		StringBuilder pageContent = new StringBuilder();
		List<Pages> pages = new ArrayList<Pages>();
		if(fileContent==null || fileContent.isEmpty())
		{
			pages.add(new Pages(0, 0, pageNumber, ""));
			return pages;
		}
		for(int i = 0; i < fileContent.length(); i++)
		{
			pageContent.append(fileContent.charAt(i));
			if (pageContent.length() == pageSize || i == fileContent.length() - 1){
				pages.add(new Pages(0, 0, pageNumber, pageContent.toString()));
				pageNumber++;
				pageContent.setLength(0);
			}
		}
		return pages;
	} 
}
