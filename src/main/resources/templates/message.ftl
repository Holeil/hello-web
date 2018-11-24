<#import "parts/common.ftl" as common>

<#include "parts/security.ftl">

<@common.page>
    <div class="card center-block mb-2">
        <div class="card-header"><h5>${message.title}</h5><h6>${message.specialty}</h6></div>
        <div class="card-body">
            <p class="card-text">${message.text}</p>
        </div>
    </div>
    <div class="border border-primary mb-2">
        <#list comments as comment>
            <div class="card">
                <div class="card-header">
                    <b>${comment.id}</b>${comment.author.username}
                </div>
                <div class="card-body">
                    ${comment.text}
                </div>
                <div class="card-footer d-flex bd-highlight">
                    <div class="p-2 flex-grow-1 bd-highlight">
                        ${comment.date}
                    </div>
                    <div class="p-2 bd-highlight">
                        <a href="/message/${message.id}/likes${comment.id}">
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
            <textarea class="form-control" name="text" placeholder="Your comments.."></textarea>
            <input type="submit" value="Submit comment" class="btn btn-primary" />
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
        </form>
    </#if>
</@common.page>