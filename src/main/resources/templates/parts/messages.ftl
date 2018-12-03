<#macro messages path isUpdateMessageForm>
    <script async src="https://imgbb.com/upload.js" data-auto-insert="viewer-links" data-sibling="download" data-palette="blue"></script>
    <form action="/profile/${user.username}/${path}<#if isUpdateMessageForm>${message.id}</#if>" method="post" class="mt-5 container">
        <h2 class="ml-2">${language.addMessage}</h2>
        <div class="d-flex row mt-3 mb-3">
            <div class="mr-auto bd-highlight col-sm-5">
                <h5 class="font-weight-bold">${language.title}: </h5>
                <input type="text" maxlength="100" name="title" value="<#if isUpdateMessageForm>${message.title}</#if>" class="form-control ${(titleError??)?string('is-invalid', '')}" placeholder="${language.title}">
                <#if titleError??>
                <div class="invalid-feedback">
                    ${titleError}
                </div>
                </#if>
            </div>
            <div class="ml-auto bd-highlight col-sm-4">
                <h5 class="font-weight-bold">${language.specialty}: </h5>
                <input type="text" maxlength="24" name="specialty" value="<#if isUpdateMessageForm>${message.specialty}</#if>" class="form-control ${(specialtyError??)?string('is-invalid', '')}" placeholder="${language.specialty}">
                <#if specialtyError??>
                <div class="invalid-feedback">
                    ${specialtyError}
                </div>
                </#if>
            </div>
        </div>
        <div class="row mb-5">
            <div class="col">
                <textarea maxlength="2000" class="form-control ${(textError??)?string('is-invalid', '')}" name="text" placeholder="${language.text}" style="resize: none; min-height: 300px"><#if isUpdateMessageForm>${message.text}</#if></textarea>
                <download style="display: inline-block;"/>
            </div>
            <#if textError??>
                <div class="invalid-feedback">
                    ${textError}
                </div>
            </#if>
        </div>
        <div class="d-flex row mb-5">
            <div class="mr-auto bd-highlight col-sm-7">
                <input type="text" name="tag" maxlength="60" value="<#if isUpdateMessageForm>${message.tag}</#if>" class="form-control ${(tagError??)?string('is-invalid', '')}" placeholder="${language.tags}: #Itransition #IStudy #ITry">
                <#if tagError??>
                <div class="invalid-feedback">
                    ${tagError}
                </div>
                </#if>
            </div>
            <div class="ml-auto bd-highlight mr-3">
                <button type="submit" class="btn btn-primary"><#if isUpdateMessageForm>${language.update}</#if><#if !isUpdateMessageForm>${language.create}</#if></button>
            </div>
        </div>
        <#if isUpdateMessageForm><input type="hidden" name="messageId" value="${message.id}" /></#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
    </form>
</#macro>