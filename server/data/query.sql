    call get_region_name_from_region_code("recruit", "region_code", "region_table");
    call get_region_name_from_region_code("company", "region_code", "company_region_table");

    select
      r.title,
      r.description,
      r.short_tip,
      r.region_code,
      r.region_detail,
			r.welfare,
      l1_min.name `min_salary`,
      l1_max.name `max_salary`,
      l1_month.name `salary_month`,
      l1_education.name education,
      l1_job_type.name job_type,
      l1_experience.name experience,
      apfs.name companyName,
      apfs.region_detail companyRegionDetail,
      apfs.logo companyLogo,
      l1_scale.name companyScale,
      concat(tmp_region_table.region_name, r.region_detail) location,
			tmp_company_region_table.region_name company_location
    from
      recruit r
    left join dts_item_level_1 l1_min on l1_min.parent_value="salary" and l1_min.value=r.`min_salary`
    left join dts_item_level_1 l1_max on l1_max.parent_value="salary" and l1_max.value=r.`max_salary`
    left join dts_item_level_1 l1_month on l1_month.parent_value="salary_month" and l1_month.value=r.`salary_month`
    left join dts_item_level_1 l1_education on l1_education.parent_value="education" and l1_education.value=r.`education`
    left join dts_item_level_1 l1_job_type on l1_job_type.parent_value="job_type" and l1_job_type.value=r.`job_type`
    left join dts_item_level_1 l1_experience on l1_experience.parent_value="experience" and l1_experience.value=r.`experience`
    left join company apfs on apfs.id=r.company_id
    left join dts_item_level_1 l1_scale on l1_scale.parent_value="company_scale" and l1_scale.value=apfs.`scale`
    left join tmp_region_table on r.id=tmp_region_table.id
		left join tmp_company_region_table on r.company_id=tmp_company_region_table.id;