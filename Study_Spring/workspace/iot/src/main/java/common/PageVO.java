package common;

public class PageVO {
	int totalList;				// 총 글의 건수
	int totalPage;				// 총 페이지수 수
	int totalBlock;				// 총 블록 수
	int pageList = 10;			// 페이지당 보여질 목록의 수
	int blockPage = 10;			// 블럭당 보여질 페이지 수
	int curPage;				// 현재 페이지
	int beginList, endList;		// 각 페이지에 보여질 시작 목록 번호, 끝 목록번호
	int curBlock;				// 현재 블럭
	int beginPage, endPage;		// 각 블럭에 보여질 시작, 끝 페이지 번호
	
	String search, keyword;		// 검색조건, 검색어
	String viewType = "list";	// 게시판 형태 ( 기본 : 목록형태)
	
	
	
	
	public String getViewType() {
		return viewType;
	}
	public void setViewType(String viewType) {
		this.viewType = viewType;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getTotalList() {
		return totalList;
	}
	public void setTotalList(int totalList) {
		this.totalList = totalList;
		totalPage = totalList / pageList;
		// 512건 / 10개 = 51.. 총 페이지 51...
		// 나머지 발생한 경우 1페이지를 증가...
		if (totalList % pageList > 0 ) ++totalPage;
		
		totalBlock = totalPage / blockPage;
		if (totalPage % blockPage > 0 ) ++totalBlock;
		
		//각 페이지의 끝 목록번호 : 총 목록수 - (페이지번호-1) * 페이지당 보여질 목록수
		endList = totalList - ( curPage -1) * pageList;
		//각 페이지의 시작 목록번호 : 끝 목록번호 - (페이지당 보여질 목록수 - 1)
		beginList = endList - (pageList - 1);
		// 블럭번호(curBlock) : 페이지번호(curPage) / 블럭당 보여질 페이지수(blockPage)
		curBlock = curPage / blockPage;
		if ( curPage % blockPage > 0 ) ++curBlock;
		
		// 각 블럭의 끝 페이지번호 : 블럭번호 * 블럭당 보여질 페이지수
		endPage = curBlock * blockPage;
		//  각 블럭의 시작 페이지번호 : 끝 페이지번호 - (블럭당 보여질 페이지수 - 1)
		beginPage = endPage - (blockPage -1);
		// 마지막 블럭에서 끝 페이지번호가 총 페이지수보다 클 수 없으므로
		// 총 페이지수를 끝 페이지번호로 한다
		if (endPage > totalPage) endPage = totalPage;
		
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalBlock() {
		return totalBlock;
	}
	public void setTotalBlock(int totalBlock) {
		this.totalBlock = totalBlock;
	}
	public int getPageList() {
		return pageList;
	}
	public void setPageList(int pageList) {
		this.pageList = pageList;
	}
	public int getBlockPage() {
		return blockPage;
	}
	public void setBlockPage(int blockPage) {
		this.blockPage = blockPage;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getBeginList() {
		return beginList;
	}
	public void setBeginList(int beginList) {
		this.beginList = beginList;
	}
	public int getEndList() {
		return endList;
	}
	public void setEndList(int endList) {
		this.endList = endList;
	}
	public int getCurBlock() {
		return curBlock;
	}
	public void setCurBlock(int curBlock) {
		this.curBlock = curBlock;
	}
	public int getBeginPage() {
		return beginPage;
	}
	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}	
}
