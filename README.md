# Taipei Tour

## 概述
這是一個台北旅遊景點介紹的 app ，支援 9 種國家語系，若曾經更新語系紀錄，下次開啟會顯示上次切換到的語系，旅遊列表在頂部時，螢幕下滑可以刷新列表，並以分頁呼叫的方式獲取資料，防止載入時間過久，點擊景點列表物件進入景點詳情，最下方有連結可以進入台北市政府官網介紹頁。

## 第三方套件
  * retrofit
  * hilt
  * shimmer
  * Glide
  
## UX
1. 開啟 app 使用客制圖片做過場圖，防止開啟時有短暫白屏
2. 列表加載圖片時設定讀取中的暫定圖片
3. 加載圖片錯誤時顯示指定錯誤圖片
4. fragment 過場時增加前滑/後滑動畫，讓使用者有換頁的感覺
5. 列表初次載入時，用 shimmer 做過場，讓使用者了解畫面正在加載
6. 滑到列表最底頁時若還有資料會顯示讀取條提醒使用者
7. 呼叫 api 失敗時，顯示彈窗提醒使用者
8. 網頁在讀取時顯示讀取條
9. 記錄上次切換的語言