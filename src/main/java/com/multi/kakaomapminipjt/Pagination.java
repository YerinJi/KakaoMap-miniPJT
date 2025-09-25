package com.multi.kakaomapminipjt;

public class Pagination {
    private final int page;
    private final int size;
    private final int total;
    private final int totalPages;
    private final int block;
    private final int startPage;
    private final int endPage;

    public Pagination(int page, int size, int total, int block) {
        this.page = Math.max(1, page);
        this.size = Math.max(1, size);
        this.total = Math.max(0, total);
        this.block = Math.max(1, block);

        this.totalPages = (int)Math.ceil((double) this.total / this.size);
        int currentBlock = (int) Math.ceil((double) this.page / this.block);

        this.startPage = (currentBlock - 1) * this.block + 1;
        int ep = this.startPage + this.block - 1;
        this.endPage = Math.min( ep , Math.max(1, this.totalPages));
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public int getTotal() {
        return total;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getBlock() {
        return block;
    }

    public int getStartPage() {
        return startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public boolean isHasPrev(){
        return startPage > 1;
    }

    public boolean isHasNext(){
        return endPage < totalPages;
    }

    public int getPrevPage(){
        return Math.max(1, startPage - 1);
    }

    public int getNextPage(){
        return Math.max(1, Math.min(endPage + 1, totalPages));
    }

    public int getOffset(){
        return (page - 1) * size;
    }
}

