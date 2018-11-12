<#import "parts/common.ftl" as common>
<#import "parts/login.ftl" as login>

<@common.page>
    <div class="ml-1">Login</div>
    ${message?ifExists}
    <@login.login "/login" false />
</@common.page>