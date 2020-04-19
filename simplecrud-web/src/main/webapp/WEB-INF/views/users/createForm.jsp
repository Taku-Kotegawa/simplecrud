<t:messagesPanel />
<form:form modelAttribute="usersForm" action="create" cssClass="form-inline">

  <table class="table">
    <tbody>
      <tr>
        <th width="100px">
          <form:label path="uid" cssErrorClass="text-danger">ユーザID
          </form:label>
        </th>
        <td>
          <form:input path="uid" cssClass="form-control" cssErrorClass="form-control is-invalid" autocomplete="off" />
          <form:errors path="uid" cssClass="invalid-feedback"/>
        </td>
      </tr>
      <tr>
        <th><form:label path="pass">パスワード</form:label></th>
        <td>
          <form:input path="pass" cssClass="form-control" cssErrorClass="form-control is-invalid" autocomplete="off" />
          <form:errors path="pass" cssClass="invalid-feedback"/>
        </td>
      </tr>
      <tr>
        <th><form:label path="name">氏名</form:label></th>
        <td>
          <form:input path="name" cssClass="form-control" cssErrorClass="form-control is-invalid" autocomplete="off" />
          <form:errors path="name" cssClass="invalid-feedback"/>
        </td>
      </tr>
      <tr>
        <th><form:label path="mail">メール</form:label></th>
        <td>
          <form:input path="mail" cssClass="form-control" cssErrorClass="form-control is-invalid" autocomplete="off" />
          <form:errors path="mail" cssClass="invalid-feedback"/>
        </td>
      </tr>
      <tr>
        <th><form:label path="comment">コメント</form:label></th>
        <td>
          <form:textarea path="comment" rows="5" cols="30" cssClass="form-control" cssErrorClass="form-control is-invalid" autocomplete="off" />
          <form:errors path="comment" cssClass="invalid-feedback"/>
        </td>
      </tr>
    </tbody>
  </table>
  <a href="list">一覧に戻る</a>
  <input type="submit" name="confirm" value="確認" />
</form:form>
