/**
 * 插入“画图”的按钮
 * 本功能依赖[Mermaid.js](https://mermaid-js.github.io)组件，请保证调用CherryMarkdown前已加载mermaid.js组件
 */
export default class ChatGpt extends MenuBase {
    constructor($cherry: any);
    openai: openAI.OpenAIApi;
    proxy: {
        host: any;
        port: any;
    };
    ignoreError: any;
    /**
     * 在编辑器中添加文字
     */
    concatText(selection: any, text: any): void;
    /**
     * 请求openai api，成功回调&失败回调
     * @param {string} name
     * @param {string} selection
     */
    queryOpenAIApi(name: string, selection: string): void;
    button: any;
}
import MenuBase from "@/toolbars/MenuBase";
import openAI from "openai";
