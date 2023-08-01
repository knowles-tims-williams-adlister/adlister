<div class="container">
  <h3>Update Password</h3>
  <form action="/update-password" method="post">
    <div class="form-group">
      <label for="old_password">Old Password</label>
      <input id="old_password" name="old_password" class="form-control" type="password">
    </div>
    <div class="form-group">
      <label for="new_password">New Password</label>
      <input id="new_password" name="new_password" class="form-control" type="password">
    </div>
    <div class="form-group">
      <label for="confirm_password">Confirm Password</label>
      <input id="confirm_password" name="confirm_password" class="form-control" type="password">
    </div>
    <input type="submit" class="btn btn-primary btn-block">
  </form>
</div>