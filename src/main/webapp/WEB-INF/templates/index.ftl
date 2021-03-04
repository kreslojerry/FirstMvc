<#include "main-template.ftl"/>

<#macro content>
    <h2>Hello, ${name}</h2>
    <hr/>
    <a href="/users">Users</a>
    <hr/>
    <a href="/new">Create new user</a>
</#macro>

<@main title="Index page"/>
