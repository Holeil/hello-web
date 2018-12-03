<#import "parts/common.ftl" as common>

<#include "parts/security.ftl">

<@common.page>
<div class="container">
    <div class="card ${siteTheme.card} center-block mb-2">
        <div class="card-header ${siteTheme.cardHeader}">
            <h5>${message.title}</h5>
            <h6>${message.specialty}</h6>
        </div>
        <div class="card-body ${siteTheme.cardBody}">
            <p class="card-text">${converter.markdownToHtml(message.text)}</p>
        </div>
        <div class="card-footer ${siteTheme.cardFooter} d-flex bd-highlight">
            <div class="mr-auto">
                ${message.tag}
            </div>
            <div class="ml-auto">
                <#include "parts/rate.ftl" >
            </div>
        </div>
    </div>
    <div class="mb-2">
        <#list comments as comment>
            <div class="card ${siteTheme.comment} mb-3">
                <div class="card-header">
                    <a href="/profile/${comment.author.username}">${comment.author.username}</a>
                </div>
                <div class="card-body ${siteTheme.commentBody}">
                    ${comment.text}
                </div>
                <div class="card-footer d-flex bd-highlight  ${siteTheme.commentFooter}">
                    <div class="p-2 flex-grow-1 bd-highlight">
                        ${comment.date}
                    </div>
                    <div class="p-2 bd-highlight ">
                        <a href="<#if user?? && isUser>/message/${message.id}/likes${comment.id}</#if>">
                        <#if comment.meLiked>
                            <i class="fas fa-heart"></i>
                        <#else>
                        <i class="far fa-heart"></i>
                        </#if>
                            ${comment.likes}
                        </a>
                    </div>
                </div>
            </div>
        </#list>
    </div>
    <#if isUser>
        <form action="/message/${message.id}/addcomment" method="post">
            <textarea class="form-control ${(commentError??)?string('is-invalid', '')}" name="text" placeholder="${language.youComment}" style="resize: none;"></textarea>
            <#if commentError??>
                <div class="invalid-feedback">
                    ${commentError}
                </div>
            </#if>
            <input type="submit" value="${language.sendComment}" class="btn btn-primary mt-1" />
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
        </form>
    </#if>
</div>
</@common.page>