set @recruit_list_count = 0;
select count(distinct region_code) into @recruit_list_count from recruit;

DROP TEMPORARY TABLE IF EXISTS temp_region_table;
CREATE TEMPORARY TABLE temp_region_table (`val_` VARCHAR(128) NOT NULL) DEFAULT CHARSET=utf8;

set @i = 0;
while @i < @recruit_list_count do
	set @region_code_item = "";
	set @sql = concat("select region_code into @region_code_item from recruit limit 1 offset ", @i);
	prepare sql from @sql;
	execute sql;
	insert into temp_region_table(val_) values (1);
	insert into temp_region_table(val_) values (@region_code_item);
	deallocate sql;
	set @i = @i + 1;
end while;

select * from temp_region_table;

