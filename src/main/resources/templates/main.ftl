<#import "parts/common.ftl" as common>
<#import "parts/login.ftl" as login>

<@common.page>
    <a href="/login"><button class="button">Login</button></a>
    <a href="/registration"><button class="button">Registration</button></a>
    <@login.logout />
    <a href="/user">Users</a>
</@common.page>