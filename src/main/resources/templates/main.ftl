<#import "parts/common.ftl" as common>

<@common.page>
    <#list messages as message>
    <div class="card center-block mb-5">
        <div class="card-header"><h5>${message.title}</h5><h6>${message.specialty}</h6></div>
        <div class="card-body">
            <p class="card-text">${message.text}</p>
        </div>
        <div class="card-footer">
            ${message.tag}
        </div>
    </div>
    </#list>
</@common.page>
