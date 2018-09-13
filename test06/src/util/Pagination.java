package util;

public class Pagination {
	// 只需得到四个数据
	private int ye;
	private int maxYe;
	private int beginYe;
	private int endYe;
	private int begin;

	// numInPage一页几条 numOfPage页码数
	public Pagination(int ye, int count, int numInPage, int numOfPage) {
		this.ye = ye;
		if (this.ye <= 1) {
			this.ye = 1;
		}
		// 三目运算符 a?b:c
		maxYe = count % numInPage == 0 ? count / numInPage : count / numInPage + 1;
		if (this.ye >= maxYe) {
			this.ye = maxYe;
		}
		beginYe = this.ye - numOfPage / 2;
		if (beginYe <= 1) {
			beginYe = 1;
		}
		endYe = beginYe + numOfPage - 1;
		if (endYe >= maxYe) {
			endYe = maxYe;
			beginYe = endYe - numOfPage + 1;
		}
		if (beginYe <= 1) {
			beginYe = 1;
		}
		begin = (this.ye - 1) * numInPage;

	}

	public int getYe() {
		return ye;
	}

	public void setYe(int ye) {
		this.ye = ye;
	}

	public int getMaxYe() {
		return maxYe;
	}

	public void setMaxYe(int maxYe) {
		this.maxYe = maxYe;
	}

	public int getBeginYe() {
		return beginYe;
	}

	public void setBeginYe(int beginYe) {
		this.beginYe = beginYe;
	}

	public int getEndYe() {
		return endYe;
	}

	public void setEndYe(int endYe) {
		this.endYe = endYe;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

}
