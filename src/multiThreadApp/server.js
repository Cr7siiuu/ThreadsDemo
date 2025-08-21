const express = require('express');
const path = require('path');

const app = express();
const port = process.env.PORT || 8080;

// Servir les fichiers Angular du dossier /public
app.use(express.static(path.join(__dirname, 'public')));

// Rediriger toutes les routes vers index.html (SPA routing)
app.get('*', (req, res) => {
  res.sendFile(path.join(__dirname, 'public', 'index.html'));
});

app.listen(port, () => {
  console.log(`🚀 Application Angular servie sur http://localhost:${port}`);
});
