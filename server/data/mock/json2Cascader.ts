import jobData from './job.json';
import regionData from './region.json';
import { writeFileSync } from 'fs';
import { resolve } from 'path';

interface DtsItem {
  label: string;
  value: number;
  children?: DtsItem[];
}

interface ResultItem extends Pick<DtsItem, 'label' | 'value'> {
  parentValue: number;
}

const json2Cascader = (dts: DtsItem[]): ResultItem[][] => {
  const result: ResultItem[][] = [];

  const dfs = (list: DtsItem[], parentValue = 0, level = 0): void => {

    list.forEach(({ label, value, children }) => {
      if (!result[level]) {
        result[level] = [];
      }
      result[level].push({ label, value, parentValue });
      children && dfs(children, value, level + 1);
    });
  };

  dfs(dts);

  return result;
};

writeFileSync(resolve(__dirname, 'jobTable.json'), JSON.stringify(json2Cascader(jobData as any)));
writeFileSync(resolve(__dirname, 'regionTable.json'), JSON.stringify(json2Cascader(regionData as any)));
