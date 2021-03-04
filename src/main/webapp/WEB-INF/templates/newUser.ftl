<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>

<#include "main-template.ftl"/>

<#macro content>
    <@sf.form action="/new" method="post" modelAttribute="user">
        <div>
            <@sf.label path="name">Name</@sf.label>
            <@sf.input path="name"/>
            <@sf.errors path="name" cssStyle="color:red"/>
        </div>
        <div>
            <@sf.label path="surname">Surname</@sf.label>
            <@sf.input path="surname"/>
            <@sf.errors path="surname" cssStyle="color:red"/>
        </div>
        <div>
            <@sf.label path="email">Email</@sf.label>
            <@sf.input path="email"/>
            <@sf.errors path="email" cssStyle="color:red"/>
        </div>
        <div>
            <@sf.label path="password">Password</@sf.label>
            <@sf.input path="password" type="password"/>
            <@sf.errors path="password" cssStyle="color:red"/>
        </div>
        <input type="submit" value="Create new user">
    </@sf.form>
</#macro>

<@main title="Add user"/>
