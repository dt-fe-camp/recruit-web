/**
 * @file index.ts
 * @author afcfzf(9301462@qq.com)
 */

export interface RespDtsItem {
  name: string;
  value: string;
  children?: RespDtsItem[];
}

export interface ComponentDtsItem {
  label: string;
  value: string;
  children?: ComponentDtsItem[];
}

export const convertToDts = (respDts: RespDtsItem[]): ComponentDtsItem[] => respDts.map(({ name, value, children }) => {
  const result: ComponentDtsItem = {
    label: name,
    value,
  };

  if (children?.length) {
    result.children = convertToDts(children);
  }

  return result;
});
