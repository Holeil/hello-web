<#import "parts/common.ftl" as common>

<@common.page>
<h2 class="mb-4">User: ${user.getUsername()}</h2>
<div class="user-profile">
    <div class="user-information">
        <div><h5>Email:</h5><span><#if user.getEmail()??>${user.getEmail()}<#else> none</#if></span></div><!--Добавить отображение того, что юзер подтвердил свой мэил, или нет-->
        <div><h5>Information about user:</h5><span>Other information..................</span></div><!--Добавить возможность редактировать свой профиль-->
    </div>
    <div class="user-notes wrapper">
        <div class="content">
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">Название</th>
                    <th scope="col">Номер специальности</th>
                    <th scope="col">Действия</a></th>
                </tr>
            </thead>
            <tbody>
                <#list messages as message>
                    <tr>
                        <td>
                            <a href="/message/${message.id}" style="text-decoration: none; color: black;">
                                ${message.title}
                            </a>
                        </td>
                        <td>
                            ${message.specialty}
                        </td>
                        <td>
                            <a class="btn btn-outline-primary btn-sm" href="/profile/${user.username}/message${message.id}" role="button">Edit</a>
                            <form action="/profile/${user.username}/deletemessage" method="post" style="display: inline-block">
                                <input type="submit" value="Delete" class="btn btn-outline-primary btn-sm" />
                                <input type="hidden" name="messageId" value="${message.id}" />
                                <input type="hidden" value="${_csrf.token}" name="_csrf"><!-- Добавить сортировки конспектов-->
                            </form>
                        </td><!-- Добавить переход на страницу конспекта-->
                    </tr>
                </#list>
            </tbody>
        </table>
        </div>
        <#if user.isRole("USER")>
            <div class="footer">
                <a class="btn btn-primary" href="/profile/${user.username}/addmessage" role="button">Create note</a>
            </div>
        </#if>
    </div>
</div>
</@common.page>