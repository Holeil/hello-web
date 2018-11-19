<#import "parts/common.ftl" as common>

<@common.page>
<h2 class="mb-4">User: ${user.getUsername()}</h2>
<div class="user-profile">
    <div class="user-information">
        <div><h5>Email:</h5><span><#if user.getEmail()??>${user.getEmail()}<#else> none</#if></span></div>
        <div><h5>Information about user:</h5><span>Other information..................</span></div>
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
                    <td>${message.title}</td>
                    <td>${message.specialty}</td>
                    <td>
                        <a class="btn btn-outline-primary btn-sm" href="#" role="button">Edit</a>
                        <form action="/user/${user.id}/deletemessage" method="post" style="display: inline-block">
                            <input type="hidden" name="userId" value="${user.id}">
                            <input type="hidden" name="id" value="${message.id}" />
                            <input type="submit" value="Delete" class="btn btn-outline-primary btn-sm" />
                            <input type="hidden" value="${_csrf.token}" name="_csrf">
                        </form>
                    </td>
                </tr>
                </#list>
            </tbody>
        </table>
        </div>
        <div class="footer">
            <a class="btn btn-primary" href="/user/${user.getId()}/addmessage" role="button">Create note</a>
        </div>
    </div>
</div>
</@common.page>