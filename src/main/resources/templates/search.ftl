<#import "parts/common.ftl" as common>
<@common.page>
<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th scope="col">
                Название
            </th>
            <th scope="col">
                Специальность
            </th>
            <th scope="col">
                Тэги
            </th>
        </tr>
        </thead>
        <tbody>
            <#list messages.content as message>
                <tr>
                    <td>
                        <a href="/message/${message.messageId}">
                            ${message.title}
                        </a>
                    </td>
                    <td>
                        ${message.specialty}
                    </td>
                    <td>
                        ${message.tag}
                    </td>
                </tr>
            </#list>
        </tbody>
    </table>
</div>
</@common.page>