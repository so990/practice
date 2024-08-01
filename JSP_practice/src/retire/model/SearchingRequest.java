package retire.model;

public class SearchingRequest {

	
	private String searchCategori; //분류(전체,이름,사원번호, 부서)
	private String searchWord;  //검색어
	
	public SearchingRequest() {}

	public SearchingRequest(String searchCategori, String searchWord) {
		this.searchCategori = searchCategori;
		this.searchWord = searchWord;
	}

	public String getSearchCategori() {
		return searchCategori;
	}

	public void setSearchCategori(String searchCategori) {
		this.searchCategori = searchCategori;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	 
	
}
