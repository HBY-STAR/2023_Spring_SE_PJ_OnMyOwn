import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue(),vueJsx()],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  server: {
    host: 'localhost', // 本机地址
    port: 80, // 前端端口
    open: true, //项目启动时自动打开浏览器
    // 反向代理，跨域
    proxy: {
      '/api': {
        target: 'http://localhost:8080', // 后端部署地址
        changeOrigin: true, // 跨域
        rewrite: path => path.replace(/^\/api/, '') // 替换掉前面axios设置的默认头

      }
    }
  },
})
