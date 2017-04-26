namespace HangmansPizzaAPI.Models
{
    using System;
    using System.Data.Entity;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Linq;

    public partial class Model1codefirst : DbContext
    {
        public Model1codefirst()
            : base("name=Model1codefirst")
        {
        }

        public virtual DbSet<ADDRESS> ADDRESSes { get; set; }
        public virtual DbSet<ADMIN> ADMINs { get; set; }
        public virtual DbSet<BRANCH> BRANCHes { get; set; }
        public virtual DbSet<CUSTOMER> CUSTOMERs { get; set; }
        public virtual DbSet<DEAL> DEALs { get; set; }
        public virtual DbSet<DEAL_BRANCH> DEAL_BRANCH { get; set; }
        public virtual DbSet<DEAL_ITEM> DEAL_ITEM { get; set; }
        public virtual DbSet<DELIVERY> DELIVERies { get; set; }
        public virtual DbSet<DELIVERY_RIDER> DELIVERY_RIDER { get; set; }
        public virtual DbSet<DRINK> DRINKs { get; set; }
        public virtual DbSet<INGREDIENT> INGREDIENTs { get; set; }
        public virtual DbSet<INGREDIENT_TYPE> INGREDIENT_TYPE { get; set; }
        public virtual DbSet<ITEM> ITEMs { get; set; }
        public virtual DbSet<MENU> MENUs { get; set; }
        public virtual DbSet<MENU_ITEM> MENU_ITEM { get; set; }
        public virtual DbSet<ORDER_ITEM> ORDER_ITEM { get; set; }
        public virtual DbSet<ORDER> ORDERS { get; set; }
        public virtual DbSet<PIZZA> PIZZAs { get; set; }
        public virtual DbSet<PIZZA_INGREDIENT> PIZZA_INGREDIENT { get; set; }
        public virtual DbSet<RIDER_ACTIVITY> RIDER_ACTIVITY { get; set; }
        public virtual DbSet<SIDE> SIDEs { get; set; }
        public virtual DbSet<TRANSACTION> TRANSACTIONs { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<ADDRESS>()
                .Property(e => e.CUSTOMER_ID)
                .HasPrecision(38, 0);

            modelBuilder.Entity<ADDRESS>()
                .Property(e => e.ADDRESS_LINE_1)
                .IsUnicode(false);

            modelBuilder.Entity<ADDRESS>()
                .Property(e => e.ADDRESS_LINE_2)
                .IsUnicode(false);

            modelBuilder.Entity<ADDRESS>()
                .Property(e => e.TOWN)
                .IsUnicode(false);

            modelBuilder.Entity<ADDRESS>()
                .Property(e => e.POSTCODE)
                .IsUnicode(false);

            modelBuilder.Entity<ADMIN>()
                .Property(e => e.FORENAME)
                .IsUnicode(false);

            modelBuilder.Entity<ADMIN>()
                .Property(e => e.SURNAME)
                .IsUnicode(false);

            modelBuilder.Entity<ADMIN>()
                .Property(e => e.USERNAME)
                .IsUnicode(false);

            modelBuilder.Entity<ADMIN>()
                .Property(e => e.PASSWORD)
                .IsUnicode(false);

            modelBuilder.Entity<BRANCH>()
                .Property(e => e.BRANCH_NAME)
                .IsUnicode(false);

            modelBuilder.Entity<BRANCH>()
                .Property(e => e.ADDRESS)
                .IsUnicode(false);

            modelBuilder.Entity<BRANCH>()
                .Property(e => e.POSTCODE)
                .IsUnicode(false);

            modelBuilder.Entity<BRANCH>()
                .HasMany(e => e.MENUs)
                .WithRequired(e => e.BRANCH)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<CUSTOMER>()
                .Property(e => e.CUSTOMER_TITLE)
                .IsUnicode(false);

            modelBuilder.Entity<CUSTOMER>()
                .Property(e => e.CUSTOMER_FORENAME)
                .IsUnicode(false);

            modelBuilder.Entity<CUSTOMER>()
                .Property(e => e.CUSTOMER_SURNAME)
                .IsUnicode(false);

            modelBuilder.Entity<CUSTOMER>()
                .Property(e => e.CUSTOMER_EMAIL)
                .IsUnicode(false);

            modelBuilder.Entity<CUSTOMER>()
                .Property(e => e.CUSTOMER_PHONE)
                .IsUnicode(false);

            modelBuilder.Entity<CUSTOMER>()
                .Property(e => e.CUSTOMER_PASSWORD)
                .IsUnicode(false);

            modelBuilder.Entity<CUSTOMER>()
                .HasMany(e => e.ADDRESSes)
                .WithRequired(e => e.CUSTOMER)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<CUSTOMER>()
                .HasMany(e => e.ORDERS)
                .WithRequired(e => e.CUSTOMER)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<DEAL>()
                .Property(e => e.DEAL_NAME)
                .IsUnicode(false);

            modelBuilder.Entity<DEAL>()
                .Property(e => e.DEAL_PRICE)
                .HasPrecision(4, 2);

            modelBuilder.Entity<DEAL>()
                .HasMany(e => e.DEAL_ITEM)
                .WithRequired(e => e.DEAL)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<DELIVERY>()
                .Property(e => e.ORDER_ID)
                .HasPrecision(38, 0);

            modelBuilder.Entity<DELIVERY>()
                .Property(e => e.DELIVERY_NOTES)
                .IsUnicode(false);

            modelBuilder.Entity<DELIVERY_RIDER>()
                .Property(e => e.RIDER_FORENAME)
                .IsUnicode(false);

            modelBuilder.Entity<DELIVERY_RIDER>()
                .Property(e => e.RIDER_SURNAME)
                .IsUnicode(false);

            modelBuilder.Entity<DELIVERY_RIDER>()
                .Property(e => e.RIDER_EMAIL)
                .IsUnicode(false);

            modelBuilder.Entity<DELIVERY_RIDER>()
                .Property(e => e.RIDER_PHONE)
                .IsUnicode(false);

            modelBuilder.Entity<DELIVERY_RIDER>()
                .Property(e => e.RIDER_PASSWORD)
                .IsUnicode(false);

            modelBuilder.Entity<DELIVERY_RIDER>()
                .Property(e => e.VEHICLE_TYPE)
                .IsUnicode(false);

            modelBuilder.Entity<DELIVERY_RIDER>()
                .HasMany(e => e.DELIVERies)
                .WithRequired(e => e.DELIVERY_RIDER)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<DELIVERY_RIDER>()
                .HasMany(e => e.RIDER_ACTIVITY)
                .WithRequired(e => e.DELIVERY_RIDER)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<DRINK>()
                .Property(e => e.UNIT_PRICE)
                .HasPrecision(5, 5);

            modelBuilder.Entity<INGREDIENT>()
                .Property(e => e.UNIT_PRICE)
                .IsUnicode(false);

            modelBuilder.Entity<INGREDIENT_TYPE>()
                .Property(e => e.INGREDIENT_TYPE1)
                .IsUnicode(false);

            modelBuilder.Entity<INGREDIENT_TYPE>()
                .Property(e => e.DESCRIPTION)
                .IsUnicode(false);

            modelBuilder.Entity<INGREDIENT_TYPE>()
                .HasMany(e => e.INGREDIENTs)
                .WithRequired(e => e.INGREDIENT_TYPE)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<ITEM>()
                .Property(e => e.ITEM_NAME)
                .IsUnicode(false);

            modelBuilder.Entity<ITEM>()
                .Property(e => e.ITEM_TYPE)
                .IsUnicode(false);

            modelBuilder.Entity<ITEM>()
                .Property(e => e.SELLING_PRICE)
                .IsUnicode(false);

            modelBuilder.Entity<ITEM>()
                .HasMany(e => e.DEAL_ITEM)
                .WithRequired(e => e.ITEM)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<ITEM>()
                .HasMany(e => e.DRINKs)
                .WithRequired(e => e.ITEM)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<ITEM>()
                .HasMany(e => e.MENU_ITEM)
                .WithRequired(e => e.ITEM)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<ITEM>()
                .HasMany(e => e.ORDER_ITEM)
                .WithRequired(e => e.ITEM)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<ITEM>()
                .HasMany(e => e.PIZZAs)
                .WithRequired(e => e.ITEM)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<ITEM>()
                .HasMany(e => e.SIDEs)
                .WithRequired(e => e.ITEM)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<MENU>()
                .Property(e => e.PRICE_MULTIPLIER)
                .HasPrecision(5, 5);

            modelBuilder.Entity<MENU>()
                .HasMany(e => e.MENU_ITEM)
                .WithRequired(e => e.MENU)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<MENU_ITEM>()
                .Property(e => e.UNIT_PRICE)
                .HasPrecision(5, 5);

            modelBuilder.Entity<ORDER_ITEM>()
                .Property(e => e.UNIT_PRICE)
                .IsUnicode(false);

            modelBuilder.Entity<ORDER>()
                .Property(e => e.CUSTOMER_ID)
                .HasPrecision(38, 0);

            modelBuilder.Entity<ORDER>()
                .Property(e => e.TOTAL_COST)
                .HasPrecision(10, 3);

            modelBuilder.Entity<ORDER>()
                .Property(e => e.ORDER_TYPE)
                .IsUnicode(false);

            modelBuilder.Entity<ORDER>()
                .Property(e => e.NOTES)
                .IsUnicode(false);

            modelBuilder.Entity<ORDER>()
                .HasMany(e => e.DELIVERies)
                .WithRequired(e => e.ORDER)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<ORDER>()
                .HasMany(e => e.ORDER_ITEM)
                .WithRequired(e => e.ORDER)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<ORDER>()
                .HasMany(e => e.TRANSACTIONs)
                .WithRequired(e => e.ORDER)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<PIZZA>()
                .Property(e => e.UNIT_PRICE)
                .IsUnicode(false);

            modelBuilder.Entity<RIDER_ACTIVITY>()
                .Property(e => e.SHIFT_LENGTH)
                .IsUnicode(false);

            modelBuilder.Entity<RIDER_ACTIVITY>()
                .Property(e => e.WAGE_MULTIPLIER)
                .HasPrecision(2, 1);

            modelBuilder.Entity<RIDER_ACTIVITY>()
                .Property(e => e.LOCATION)
                .IsUnicode(false);

            modelBuilder.Entity<RIDER_ACTIVITY>()
                .Property(e => e.STATUS)
                .IsUnicode(false);

            modelBuilder.Entity<SIDE>()
                .Property(e => e.UNIT_PRICE)
                .HasPrecision(5, 5);

            modelBuilder.Entity<TRANSACTION>()
                .Property(e => e.PAYMENT_METHOD)
                .IsUnicode(false);

            modelBuilder.Entity<TRANSACTION>()
                .Property(e => e.DETAIL_1)
                .IsUnicode(false);

            modelBuilder.Entity<TRANSACTION>()
                .Property(e => e.DETAIL_2)
                .IsUnicode(false);

            modelBuilder.Entity<TRANSACTION>()
                .Property(e => e.DETAIL_3)
                .IsUnicode(false);

            modelBuilder.Entity<TRANSACTION>()
                .Property(e => e.DETAIL_4)
                .IsUnicode(false);

            modelBuilder.Entity<TRANSACTION>()
                .Property(e => e.TRANSACTION_VALUE)
                .HasPrecision(10, 3);
        }
    }
}
