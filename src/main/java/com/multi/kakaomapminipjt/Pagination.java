package com.multi.kakaomapminipjt;

public class Pagination {
    private final int page;
    private final int size;
    private final int allData;
    private final int totalPages;
    private final int firstPage;
    private final int lastPage;
    private final int pageNum;

    public Pagination(int page, int size, int allData, int pageNum) {
        this.page = Math.max(1, page);
        this.size = Math.max(1, size);
        this.allData = Math.max(0, allData);
        this.pageNum = Math.max(1, pageNum);
        this.totalPages = (int)Math.ceil((float) this.allData / this.size);
        int currentPageNum = (int) Math.ceil((float) this.page / this.pageNum);

        this.firstPage = (currentPageNum - 1) * this.pageNum + 1;
        int ep = this.firstPage + this.pageNum - 1;
        this.lastPage = Math.min( ep , Math.max(1, this.totalPages));
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public int getAllData() {
        return allData;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public boolean isHasPrev(){
        return firstPage > 1;
    }

    public boolean isHasNext(){
        return lastPage < totalPages;
    }

    public int getPrevPage(){
        return Math.max(1, firstPage - 1);
    }

    public int getNextPage(){
        return Math.max(1, Math.min(lastPage + 1, totalPages));
    }

    public int getOffset(){
        return (page - 1) * size;
    }
}
