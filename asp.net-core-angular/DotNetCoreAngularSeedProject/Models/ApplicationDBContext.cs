using Microsoft.EntityFrameworkCore;

namespace DotNetCoreAngularSeedProject.Models
{
    public class ApplicationDBContext : DbContext
    {
        public ApplicationDBContext(DbContextOptions<ApplicationDBContext> options)
            : base(options)
        { }

        public DbSet<Conference> Conferences { get; set; }         
        public DbSet<Session> Sessions { get; set; }
    }
}