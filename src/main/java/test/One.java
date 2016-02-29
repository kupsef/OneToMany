package test;

import javax.persistence.*;
import java.util.Set;

@javax.persistence.Entity(name="One")
public class One {
	@javax.persistence.OneToMany(mappedBy="m", cascade = CascadeType.PERSIST)
	protected java.util.Set<Many> many = com.google.common.collect.Sets.newHashSet();
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long primaryKey;
	private String name;

	public Set<Many> getMany() {
		return many;
	}

	public void setMany(Set<Many> many) {
		this.many = many;
	}

	public Long getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(Long primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
