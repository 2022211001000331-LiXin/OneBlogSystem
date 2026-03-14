/**
 * 预览区域的响应式工具栏
 */
export default class PreviewerBubble {
    /**
     *
     * @param {import('../Previewer').default} previewer
     */
    constructor(previewer: import('../Previewer').default);
    /**
     * @property
     * @type {import('../Previewer').default}
     */
    previewer: import('../Previewer').default;
    /**
     * @property
     * @type {import('../Editor').default}
     */
    editor: import('../Editor').default;
    previewerDom: HTMLDivElement;
    enablePreviewerBubble: boolean;
    /**
     * @property
     * @type {{ [key: string]: HTMLDivElement}}
     */
    bubble: {
        [key: string]: HTMLDivElement;
    };
    /**
     * @property
     * @type {{ [key: string]: { emit: (...args: any[]) => any, [key:string]: any }}}
     */
    bubbleHandler: {
        [key: string]: {
            [key: string]: any;
            emit: (...args: any[]) => any;
        };
    };
    init(): void;
    removeHoverBubble: import("lodash").DebouncedFunc<() => void>;
    /**
     * 是否为由cherry生成的表格，且不是简单表格
     * @param {HTMLElement} element
     * @returns {boolean}
     */
    isCherryTable(element: HTMLElement): boolean;
    $onMouseOver(e: any): void;
    $onMouseOut(): void;
    $dealCheckboxClick(e: any): void;
    checkboxIdx: number;
    $onClick(e: any): void;
    $onChange(e: any): void;
    $getClosestNode(node: any, targetNodeName: any): any;
    /**
     * 处理复制代码块的操作
     */
    $dealCopyCodeBlock(e: any): boolean;
    /**
     * 隐藏预览区域已经激活的工具栏
     * @param {string} trigger 移除指定的触发方式，不传默认全部移除
     */
    $removeAllPreviewerBubbles(trigger?: string): void;
    /**
     * 为触发的table增加操作工具栏
     * @param {string} trigger 触发方式
     * @param {HTMLElement} htmlElement 用户触发的table dom
     */
    $showTablePreviewerBubbles(trigger: string, htmlElement: HTMLElement): void;
    /**
     * 为选中的图片增加操作工具栏
     * @param {HTMLImageElement} htmlElement 用户点击的图片dom
     */
    $showImgPreviewerBubbles(htmlElement: HTMLImageElement): {
        emit: () => void;
    };
    totalImgs: number;
    imgIndex: number;
    getValueWithoutCode(): string;
    /**
     * TODO: beginChangeDrawioImg 和 beginChangeImgValue 代码高度重合，后面有时间重构下，抽成一个可以复用的，可以避开代码块、行内代码影响的通用方法
     * 修改draw.io图片时选中编辑区域的对应文本
     * @param {*} htmlElement 图片node
     */
    beginChangeDrawioImg(htmlElement: any): boolean;
    /**
     * 选中图片对应的MD语法
     * @param {*} htmlElement 图片node
     * @returns {boolean}
     */
    beginChangeImgValue(htmlElement: any): boolean;
    imgAppend: string | boolean;
    /**
     * 修改图片尺寸时的回调
     * @param {HTMLElement} htmlElement 被拖拽的图片标签
     * @param {Object} style 图片的属性（宽高、对齐方式）
     */
    changeImgValue(htmlElement: HTMLElement, style: any): void;
    /**
     * 预览区域编辑器的容器
     * @param {string} trigger 触发方式
     * @param {string} type 容器类型（用作样式名：cherry-previewer-{type}）
     */
    $createPreviewerBubbles(trigger?: string, type?: string): void;
    $showBorderBubbles(): void;
    $showBtnBubbles(): void;
    /**
     * 修改预览区域代码语言设置的回调
     */
    $codePreviewLangSelectEventHandler(event: any): void;
}
