import { ChatGPTAPI } from 'chatgpt'
import fs from 'fs';

async function main() {

  var data = fs.readFileSync('article.txt');
  var text = data.toString("utf8");
  text = text.replace(/\s*/g, "");
  text = text.replace('图片', "");
  text = text.replace(/[`:_.~!@#$%^&*() \+ =<>?"{}|, \/ ;' \\ [ \] ·~！@#￥%……&*（）—— \+ ={}|《》？：“”【】、；‘’，。、]/g, '');
  const api = new ChatGPTAPI({
    apiKey: ''
  })


  console.log(text.length);
  const res = await api.sendMessage(text);
  console.log(res.text);
}

main();
