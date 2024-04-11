
# Hibernate One to One mapping @OneToOne

This project demonstrates the usage of @OneToOne annotation along with cascade and @JoinColumn. 

- This project shows the implementation of bidirectional mapping.
- The Entity with @OneToOne and without mappedBy is the owner of the relationship and this entity stores the foreign key in the table.


## Lessons Learned

- About usage of cascade when using @OneToOne annotation.
- In case of @OneToOne mapping, the child entity is fetched eagerly by default, which means fetch = FetchType.EAGER applies by default.
- If we mention fetch = FetchType.LAZY on the passport field in Person class then when you fetch Person, the details of passport will not be fetched. Passport details will be fetched when you use the passport for the first time in the code.
- fetch = FetchType.LAZY in @OneToOne mapping may not be beneficial in most of the cases.
- Be mindful of infinite recursion when using bidirectional one to one mapping. It can affect toString implementation. One way to avoid StackOverflowError due to infinite recursion is to carefully design your toString() implementation for the entities involved. See my example where I do not fetch the entire passport details but just the passport number.
- By default, the relationship is optional and this is defined in @OneToOne annotation definition.
- You can specify the relationship to be mandatory (i.e. a Person must have a passport) by making use of optional = false in the @OneToOne mapping.
- Secondary Table: Sometimes the relationship between 2 tables may not be reflected in the Entity at the class level. For example, sometimes we have an extension table in the database that stores extra information about an object. This extension table may not be an Entity in JPA. This extension table is called the Secondary table.
- In the case of the Secondary table, the secondary table is not represented as an Entity in the application, but it only exists in the database.
  Contrast this with the Jpa @Embedded and @Embeddable (the concept of value object).


