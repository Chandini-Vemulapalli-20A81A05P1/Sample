import react from '@vitejs/plugin-react-swc'
import { defineConfig } from 'vite'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    host: '172.18.0.1',  // Allows access from other devices on your local network
    port: 3000,       // Specify the port you want to use
  },
})
