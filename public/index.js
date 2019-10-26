function encodeParams(data) {
  return typeof data == 'string' ? data : Object.keys(data).map(
    function(k){ return encodeURIComponent(k) + '=' + encodeURIComponent(data[k]) }
  ).join('&');
}

function ajax(opts) {
  var xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");
  xhr.open(opts.method, opts.url);
  xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');

  if (!!opts.success) {
    xhr.onreadystatechange = function() {
      if (xhr.readyState>3 && xhr.status==200) { opts.success(xhr.responseText); }
    };
  }

  if (!!opts.token) {
    xhr.setRequestHeader('Authorization', 'Bearer ' + opts.token);
  }

  if (!!opts.accept) {
    xhr.setRequestHeader('Accept', opts.accept);
  }

  if (!!opts.data) {
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.send(encodeParams(opts.data));
  } else {
    xhr.send();
  }

  return xhr;
}

function withElements(className, f) {
  document.querySelectorAll("." + className).forEach(f);
}

function toggle(classes, whichEnabled) {
  [...Array(classes.length).keys()].forEach(i => {
    withElements(classes[i], e => {
      e.style.display = (whichEnabled == i) ? '' : 'none';
    });
  });
}

function success() {
  toggle(['before-signup', 'after-signup'], 1);
}

function signup() {
  let email = document.querySelector("#email").value;

  console.log("Got email:", email);
  // ajax({method: "POST",
  //       url: "<your backend>",
  //       data: {email: email},
  //       success: success});

  success()
}
