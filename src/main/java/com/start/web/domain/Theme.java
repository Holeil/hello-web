package com.start.web.domain;

public enum Theme {
    MATHEMATICS("navbar-dark bg-primary",
                "btn-dark",
                "bg-danger",
                "bg-dark text-white",
                "bg-white text-dark",
                "text-black bg-light",
                "border-info text-dark",
                "text-info",
                "text-white bg-success"),

    HUMANITARIAN("navbar-dark bg-dark",
                 "btn-primary",
                 "bg-dark",
                 "bg-warning text-black",
                 "bg-dark text-white",
                 "bg-dark text-white",
                 "border-primary",
                 "",
                 "text-white bg-dark");

    private String navbar;
    private String navbarButton;
    private String card;
    private String cardHeader;
    private String cardBody;
    private String cardFooter;
    private String comment;
    private String commentBody;
    private String commentFooter;


    private Theme(String navbar, String navbarButton, String card, String cardHeader, String cardBody,
                  String cardFooter, String comment, String commentBody, String commentFooter) {
        this.navbar = navbar;
        this.navbarButton = navbarButton;
        this.card = card;
        this.cardHeader = cardHeader;
        this.cardBody = cardBody;
        this.cardFooter = cardFooter;
        this.comment = comment;
        this.commentBody = commentBody;
        this.commentFooter = commentFooter;
    }

    public String getNavbar() {
        return navbar;
    }

    public String getCard() {
        return card;
    }

    public String getComment() {
        return comment;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public String getCommentFooter() {
        return commentFooter;
    }

    public String getCardHeader() {
        return cardHeader;
    }

    public String getCardFooter() {
        return cardFooter;
    }

    public String getCardBody() {
        return cardBody;
    }

    public String getNavbarButton() {
        return navbarButton;
    }
}