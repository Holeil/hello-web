<#import "login.ftl" as login>

<#include "security.ftl">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/">Hello web</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/user">User List</span></a>
                </li>
            </#if>
        </ul>
        <#if user??>
            <div class="navbar-text mr-3">
                <a class="nav-link" href="<#if isAdmin>/user/${userId}</#if>
                                           <#if !isAdmin>/user/profile</#if>">${name}</span></a>
            </div>
            <@login.logout />
        </#if>
        <#if !user??>
            <a href="/login"><button type="submit" class="btn btn-primary">
                Login</button></span>
            </a>
        </#if>
    </div>
</nav>