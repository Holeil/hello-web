<#import "parts/common.ftl" as common>
<#import "parts/pager.ftl" as p>

<@common.page>
<h2 class="mb-4">${language.user}: ${user.username}</h2>
<div class="user-profile">
    <div class="user-information">
        <div><h5>${language.email}:</h5><span><#if user.getEmail()??>${user.getEmail()}<#else>none</#if></span></div>
        <div>
            <h5>${language.userInform}:</h5>
            <span id="user-info"><#if user.info??>${user.info}<#else>${language.userInform}</#if></span>
            <form id="send-info" action="/profile/${user.username}/changeuserinfo" method="post" hidden>
                <textarea id="input-user-info" style="width: 100%; resize: none" name="userInfo" ><#if user.info??>${user.info}<#else >${language.userInform}</#if></textarea>
                <input type="hidden" value="${_csrf.token}" name="_csrf" />
            </form>
        </div>
    </div>
    <div class="user-notes wrapper">
        <div class="content">
        <table class="table">
            <thead>
                <tr>
                    <th scope="col" class="text-center">${language.title}</th>
                    <th scope="col" class="text-center">${language.specialty}</th>
                    <#if access>
                        <th scope="col" class="text-center">${language.action}</a></th>
                    </#if>
                </tr>
            </thead>
            <tbody>
            <div class="d-flex bd-highlight">
                <div class="flex-grow-1 bd-highlight">
                    <@p.pager url page />
                </div>
                <div class="bd-highlight">
                    <#if access>
                        <a class="btn btn-primary mt-3" href="/profile/${user.username}/addmessage" role="button">${language.create}</a>
                    </#if>
                </div>
            </div>
                <#list page.content as message>
                    <tr>
                        <td class="text-center">
                            <a href="/message/${message.id}">
                                ${message.title}
                            </a>
                        </td>
                        <td class="text-center">
                            ${message.specialty}
                        </td>
                        <#if access>
                        <td class="text-center">
                            <a class="btn btn-outline-primary btn-sm" href="/profile/${user.username}/updatemessage${message.id}" role="button">${language.edit}</a>
                            <form action="/profile/${user.username}/deletemessage" method="post" style="display: inline-block">
                                <input type="submit" value="${language.delete}" class="btn btn-outline-primary btn-sm" />
                                <input type="hidden" name="messageId" value="${message.id}" />
                                <input type="hidden" value="${_csrf.token}" name="_csrf"><!-- Добавить сортировки конспектов-->
                            </form>
                        </td>
                        </#if>
                    </tr>
                </#list>
            </tbody>
        </table>
        </div>
    </div>
</div>
    <#if access><script src="/script/editInPlace.js"></script></#if>
</@common.page>