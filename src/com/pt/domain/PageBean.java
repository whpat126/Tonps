package com.pt.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PageBean {
	private int currentPage = 0; // 当前是第几页
	private int rowsPerPage = 0; // 每页多少行数据
	private int totalRowCount = 0; // 总行数
	private int totalPage = 0; // 总页数
	private List<?> dataList = null;
	/**
	 * 注意：ResultSet对象，ResultSet必须是可滚动的数据集，可以通过以下的Statement执行查询。
	 * PreparedStatement prep = iConn.prepareStatement(
	 * sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	 * 
	 * @param pageNum
	 * @param rowsNum
	 * @param rs
	 */
	public PageBean(int pageNum, int rowsNum, ResultSet rs) {
		int totalRowCountTemp = 0;
		try {
			rs.last(); //将指针指向最后一行
			totalRowCountTemp = rs.getRow(); // 总行数
			rs.beforeFirst(); //移动到第一行
			int startNum = (pageNum - 1) * rowsNum; 
			if (startNum > 0) {
				rs.absolute(startNum); // 定位到请求页的第一条记录
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		totalRowCount = totalRowCountTemp;// 设置总的行数.
		rowsPerPage = rowsNum;// 每页显示多少行
		setTotalPage(totalRowCountTemp); // 设置总的页数
		if (pageNum > totalPage) {
			pageNum = totalPage; // 用户输入的页数大于了总的页数
		} else if (pageNum < 1) {
			pageNum = 1; // 用户输入的页数小于了 1
		}
		// 当前第几页
		currentPage = pageNum;
	}

	/**
	 * 设置第页显示多少行
	 */
	public void setRowsPerPage(int rows) {
		rowsPerPage = rows;
	}
	/**
	 * 	显示多少行
	 * @return
	 */
	public int getRowsPerPage() {
		return rowsPerPage;
	}

	/** 设置当前显示多少页 */
	public void setCurPage(int page) {
		currentPage = page;
	}
	/** 当前显示多少页
	 * @return  */
	public int getCurPage() {
		return currentPage;
	}

	/** 设置总页数(根据总行数计算总页数)*/
	public void setTotalPage(int RowCount) {
		if (RowCount % rowsPerPage == 0) {
			totalPage = RowCount / rowsPerPage;
		} else {
			totalPage = RowCount / rowsPerPage + 1;
		}
	}
	/** 总页数
	 *  @return*/
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * @param 设置总的行数
	 * 
	 */
	public void setTotalRowCount(int rows) {
		totalRowCount = rows;
	}
	/**
	 * 总的行数
	 * @return
	 */
	public int getTotalRowCount() {
		return totalRowCount;
	}
	/**
	 * 当前是第几页
	 * @return
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	/**
	 * 返回的是数据库中取到的集合
	 * @return
	 */
	public List<?> getDataList() {
		return dataList;
	}

	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}

}
