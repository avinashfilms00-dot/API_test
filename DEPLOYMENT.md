# Deployment

## Backend on Render

1. Push this repository to GitHub.
2. In Render, create a new Blueprint or Web Service from the GitHub repository.
3. Render can use `render.yaml`, which builds the backend with `Dockerfile`.
4. After deploy, the backend endpoint is:

```text
https://YOUR_RENDER_SERVICE.onrender.com/bfhl
```

## Frontend on GitHub Pages

1. In the GitHub repository, open Settings > Pages.
2. Set Source to GitHub Actions.
3. Open Settings > Secrets and variables > Actions > Variables.
4. Add this repository variable after the Render backend is deployed:

```text
VITE_API_URL=https://YOUR_RENDER_SERVICE.onrender.com/bfhl
```

5. Push to the `main` branch. The workflow at `.github/workflows/deploy-frontend.yml` will build and publish the frontend.

The frontend URL will be:

```text
https://YOUR_GITHUB_USERNAME.github.io/YOUR_REPOSITORY_NAME/
```
