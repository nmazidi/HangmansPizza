﻿//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace HangmansPizzaAPI
{
    using System;
    using System.Data.Entity;
    using System.Data.Entity.Infrastructure;
    
    public partial class Entities : DbContext
    {
        public Entities()
            : base("name=Entities")
        {
        }
    
        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            throw new UnintentionalCodeFirstException();
        }
    
        public virtual DbSet<ADMIN> ADMINs { get; set; }
        public virtual DbSet<BRANCH> BRANCHes { get; set; }
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
        public virtual DbSet<CUSTOMER> CUSTOMERs { get; set; }
        public virtual DbSet<ADDRESS> ADDRESSES { get; set; }
    }
}
