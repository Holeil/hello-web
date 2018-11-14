<#import "parts/common.ftl" as common>

<@common.page>
    List of Users
    <table>
        <thead>
            <tr>
                <th>
                    Username
                </th>
                <th>
                    Role
                </th>
                <th>
                    Change
                </th>
            </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>
                    ${user.getUsername()}
                </td>
                <td>
                    <#list user.roles as role>
                        ${role} <#sep>,
                    </#list>
                </td>
                <td>
                    <a href="/user/${user.id}">edit</a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</@common.page>