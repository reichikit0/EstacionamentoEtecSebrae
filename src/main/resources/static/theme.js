(function () {
  var KEY = 'theme';
  var root = document.documentElement;

  function saved() {
    try { return localStorage.getItem(KEY); } catch (e) { return null; }
  }

  // Usa a escolha salva; se não houver, segue o tema do sistema
  var initial = saved() ||
    (window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light');

  // Aplica logo no início para evitar "piscar" de tema claro
  root.setAttribute('data-theme', initial);

  function apply(theme, btn) {
    root.setAttribute('data-theme', theme);
    try { localStorage.setItem(KEY, theme); } catch (e) {}
    if (btn) {
      btn.textContent = theme === 'dark' ? '☀️' : '🌙';
      var label = theme === 'dark' ? 'Ativar modo claro' : 'Ativar modo escuro';
      btn.setAttribute('aria-label', label);
      btn.title = label;
    }
  }

  document.addEventListener('DOMContentLoaded', function () {
    var btn = document.createElement('button');
    btn.type = 'button';
    btn.className = 'theme-toggle';
    document.body.appendChild(btn);
    apply(root.getAttribute('data-theme'), btn);

    btn.addEventListener('click', function () {
      apply(root.getAttribute('data-theme') === 'dark' ? 'light' : 'dark', btn);
    });
  });
})();