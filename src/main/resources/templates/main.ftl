<#import "parts/common.ftl" as common>

<@common.page>
    <#list messages as message>
    <a href="/message/${message.id}" class="card center-block mb-5" style="text-decoration: none; color: black;">
        <div class="card-header"><h5>${message.title}</h5><h6>${message.specialty}</h6></div>
        <div class="card-body">
            <p class="card-text">${message.text}</p>
        </div>
        <div class="card-footer">
            ${message.tag}
        </div>
    </a>
    </#list>
</@common.page>
