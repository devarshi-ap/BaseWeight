import react from '@vitejs/plugin-react'
import { defineConfig } from 'vite'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],

  // Setup Vite proxies to let local frontend dev server quietly forward API requests to backend server, bypassing CORS errors during development
  // frontend runs on localhost:5173 vs. backend on localhost:8080 (port mismatch, so browser treats them as diff origins and throws CORS error)
  server: {
    proxy: {
      '/api': 'http://localhost:8080',
    },
  },
})
