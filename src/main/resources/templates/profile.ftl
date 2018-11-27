<#import "parts/common.ftl" as common>
<#import "parts/pager.ftl" as p>

<@common.page>
<h2 class="mb-4">${language.user}: ${user.getUsername()}</h2>
<div class="user-profile">
    <div class="user-information">
        <div><h5>${language.email}:</h5><span><#if user.getEmail()??>${user.getEmail()}<#else>none</#if></span></div><!--Добавить отображение того, что юзер подтвердил свой мэил, или нет-->
        <div><h5>${language.userInform}:</h5><span>${language.userInform}</span></div><!--Добавить возможность редактировать свой профиль-->
    </div>
    <div class="user-notes wrapper">
        <div class="content">
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">${language.title}</th>
                    <th scope="col">${language.specialty}</th>
                    <#if access>
                        <th scope="col">${language.action}</a></th>
                    </#if>
                </tr>
            </thead>
            <tbody>
            <@p.pager url page />
                <#list page.content as message>
                    <tr>
                        <td>
                            <a href="/message/${message.id}">
                                ${message.title}
                            </a>
                        </td>
                        <td>
                            ${message.specialty}
                        </td>
                        <#if access>
                        <td>
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
        <div class="footer">
        <#if access>
            <a class="btn btn-primary" href="/profile/${user.username}/addmessage" role="button">${language.create}</a>
        </#if>
        </div>
    </div>
</div>
</@common.page>