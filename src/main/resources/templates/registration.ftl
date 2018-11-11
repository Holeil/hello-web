<#import "parts/common.ftl" as common>
<#import "parts/login.ftl" as login>

<@common.page>
    <div class="ml-1">Registration</div>
    ${message?ifExists}
    <@login.login "/registration" true />
</@common.page>