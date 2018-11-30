<#import "parts/common.ftl" as common>
<#import "parts/pager.ftl" as p>

<@common.page>
<div class="container">
    <h2 class="mb-4">${language.user}: ${user.username}</h2>
    <div class="">
        <div class="d-flex flex-row">
            <div class="d-flex flex-column flex-grow-1 bd-highlight">
                <div class="d-flex">
                    <div class="d-flex">
                        <h5 class="font-weight-bold">${language.email}:</h5>
                        <span class="ml-3"><#if user.getEmail()??>${user.getEmail()}<#else>none</#if></span>
                    </div>
                </div>
                <div class="mt-4" style="word-wrap: break-word;">
                    <h5 class="font-weight-bold">${language.userInform}:</h5>
                    <span id="user-info"><#if user.info??>${user.info}<#else>${language.userInform}</#if></span>
                    <form id="send-info" action="/profile/${user.username}/changeuserinfo" method="post" hidden>
                        <textarea id="input-user-info" style="resize: none; width: 100%; height: 150px;" name="userInfo" maxlength="600" class="form-control row"><#if user.info??>${user.info}<#else >${language.userInform}</#if></textarea>
                        <input type="hidden" value="${_csrf.token}" name="_csrf" />
                    </form>
                </div>
            </div>
            <div class="col-sm-5 bd-highlight">
                <h5 class="font-weight-bold">Вопросы:
                </h5>
            </div>
        </div>
        <div class="mt-3">
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
                    <div class=" ml-auto bd-highlight">
                        <#if access>
                            <div class="btn-group row" role="group" aria-label="Basic example">
                                <form action="/profile/${user.username}/sort" method="post">
                                    <input type="submit" name="byDate" value="Сортировать по Дате" role="button" class="btn btn-primary mt-3"/><!-- Язык-->
                                    <input type="submit" name="byTitle" value="Сортировать по Названию" role="button" class="btn btn-primary mt-3"/><!-- Язык-->
                                    <input type="hidden" value="${_csrf.token}" name="_csrf">
                                </form>
                                <a class="btn btn-primary mt-3" href="/profile/${user.username}/addmessage" role="button">${language.create}</a>
                            </div>
                            <div class="row mt-1 mb-1">
                                <form action="/profile/${user.username}/filter" method="get" class="d-flex bd-highlight">
                                    <input type="text" maxlength="60" name="filter" class=" form-control col flex-grow-1 bd-highlight" placeholder="Тэг, Название, Специальность."/><!-- Язык-->
                                    <input type="submit" value="Фильтр" class="btn btn-primary bd-highlight" />
                                </form>
                            </div>
                        </#if>
                    </div>
                </div>
                    <#list messages as message>
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